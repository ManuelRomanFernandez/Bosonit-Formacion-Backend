from fastapi import APIRouter, Depends, HTTPException, status
from pydantic import BaseModel
from fastapi.security import OAuth2PasswordBearer, OAuth2PasswordRequestForm
from passlib.context import CryptContext
from datetime import datetime, timedelta
from jose import jwt, JWTError

ALGORITHM = "HS256"
ACCESS_TOKEN_DURATION = 10
SECRET = "179e881408276efae7c1883391682124fa1349259d75985788d927a5c5d382c7"

router = APIRouter()

oauth2 = OAuth2PasswordBearer(tokenUrl="login")

crypt = CryptContext(schemes=["bcrypt"])


class User(BaseModel):
    username: str
    full_name: str
    email: str
    disabled: bool


class UserDB(User):
    password: str


users_db = {
    "user1": {
        "username": "user1",
        "full_name": "1",
        "email": "1",
        "disabled": False,
        "password": "$2a$12$dYgD7jC0Rq2biCySZVemgOEElTEVH/pes8H/astL/Z.vIm8A1ALHW"
    },
    "user2": {
        "username": "user2",
        "full_name": "Manuel Roman",
        "email": "marofe@email.com",
        "disabled": False,
        "password": "$2a$12$dYgD7jC0Rq2biCySZVemgOEElTEVH/pes8H/astL/Z.vIm8A1ALHW"
    },
    "user3": {
        "username": "user3",
        "full_name": "Raquel Alba",
        "email": "raqalb@email.com",
        "disabled": True,
        "password": "$2a$12$d5WnpTj7RauToskmbwYp4Oz2xkCjt3lVvl2czLCW5mj//oAfSyMR2"
    }
}


def search_user(username: str):
    if username in users_db:
        return User(**users_db[username])


def search_user_db(username: str):
    if username in users_db:
        return UserDB(**users_db[username])


async def auth_user(token: str = Depends(oauth2)):
    exception = HTTPException(
                status_code=status.HTTP_401_UNAUTHORIZED,
                detail="wrong credentials",
                headers={"WWW-Authenticate": "Bearer"})
    try:
        username = jwt.decode(token, SECRET, algorithms=ALGORITHM).get("sub")
        if username is None:
            raise exception
        return search_user(username)

    except JWTError:
        raise exception


def current_user(user: User = Depends(auth_user)):
    if user.disabled:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="disabled user",
            headers={"WWW-Authenticate": "Bearer"})
    return user


@router.post("/login")
async def login(form: OAuth2PasswordRequestForm = Depends()):
    user_db = users_db.get(form.username)
    if not user_db:
        raise HTTPException(status_code=400, detail="wrong user")

    user = search_user_db(form.username)

    if not crypt.verify(form.password, user.password):
        raise HTTPException(status_code=400, detail="wrong credentials")

    access_token = {
        "sub": user.username,
        "exp": datetime.utcnow() + timedelta(minutes=ACCESS_TOKEN_DURATION)
    }

    return {"access_token": jwt.encode(access_token, SECRET, algorithm=ALGORITHM), "token_type": "jwt"}


@router.get("/users/me")
async def me(user: User = Depends(current_user)):
    return user