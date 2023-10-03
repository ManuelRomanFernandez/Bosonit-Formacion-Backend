from fastapi import APIRouter, HTTPException
from pydantic import BaseModel


class User(BaseModel):
    id: int
    name: str
    surname: str
    age: int
    url: str | None = None


users_list = [User(id=1, name="A", surname="A", age=14, url="A"),
              User(id=2, name="B", surname="B", age=24, url="B"),
              User(id=3, name="C", surname="C", age=34, url="C")]

# python -m uvicorn users:app --reload
router = APIRouter(prefix="/users", tags=["users"], responses={404: {"message": "not found"}})


# URL: http://localhost/8000/users
@router.get("/")
async def get_all_users():
    return users_list


# URL: http://localhost:8000/users/{id}
@router.get("/{id}")
async def get_user_by_path(id: int):
    return search_user(id)


# URL: http://localhost:8000/userquery
@router.get("/userquery/")
async def get_user_by_param(id: int):
    return search_user(id)


@router.post("/", status_code=201)
async def add_user(user: User):
    if not search_user(user.id) == user:
        users_list.append(user)
        return user
    else:
        raise HTTPException(status_code=404, detail="user already exists")


@router.put("/")
async def update_user(user: User):
    found: bool = False

    for index, saved_user in enumerate(users_list):
        if saved_user.id == user.id:
            users_list[index] = user
            found = True

    if found:
        return user
    else:
        raise HTTPException(status_code=404, detail="user not found")


@router.delete("/{id}")
async def delete_user(id: int):
    found: bool = False

    for index, saved_user in enumerate(users_list):
        if saved_user.id == id:
            del users_list[index]
            found = True

    if not found:
        return {"error": "user not found"}


def search_user(id: int):
    users = filter(lambda user: user.id == id, users_list)
    try:
        return list(users)[0]
    except:
        return HTTPException(status_code=404, detail="user not found")
