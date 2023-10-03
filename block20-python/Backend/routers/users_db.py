from fastapi import APIRouter, HTTPException, status
from db.models.user import User
from db.client import db_client
from db.schemas.user import user_schema, users_schema
from bson import ObjectId

# python -m uvicorn users:app --reload
router = APIRouter(
    prefix="/usersdb",
    tags=["usersdb"],
    responses={status.HTTP_404_NOT_FOUND: {"message": "not found"}})


# URL: http://localhost/8000/users
@router.get("/", response_model=list[User])
async def get_all_users():
    return users_schema(db_client.local.users.find())


# URL: http://localhost:8000/users/{id}
@router.get("/{id}", response_model=User)
async def get_user_by_path(id: str):
    return search_user("_id", ObjectId(id))


# URL: http://localhost:8000/userquery
@router.get("/userquery/", response_model=User)
async def get_user_by_param(id: str):
    return search_user("_id", ObjectId(id))



@router.post("/", response_model=User, status_code=status.HTTP_201_CREATED)
async def add_user(user: User):
    if type(search_user("email", user.email)) == User:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="user already exists")

    user_dict = dict(user)
    del user_dict["id"]

    id = db_client.local.users.insert_one(user_dict).inserted_id

    new_user = user_schema(db_client.local.users.find_one({"_id": id}))

    return User(**new_user)


@router.put("/", response_model=User)
async def update_user(user: User):
    user_dict = dict(user)
    del user_dict["id"]

    try:
        db_client.local.users.find_one_and_replace({"_id": ObjectId(user.id)}, user_dict)
    except:
        return {"error": "user not found"}

    return search_user("_id", ObjectId(user.id))


@router.delete("/{id}", status_code=status.HTTP_204_NO_CONTENT)
async def delete_user(id: str):
    found = db_client.local.users.find_one_and_delete({"_id": ObjectId(id)})

    if not found:
        return {"error": "user not found"}


def search_user(field: str, key):
    try:
        user = db_client.local.users.find_one({field: key})
        return User(**user_schema(user))
    except:
        return HTTPException(status_code=404, detail="user not found")
