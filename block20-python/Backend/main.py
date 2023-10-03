from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles
from routers import products, users, basic_auth_users, jwt_auth_users, users_db


app = FastAPI()


# Routers
app.include_router(products.router)
app.include_router(users.router)
app.include_router(basic_auth_users.router)
app.include_router(jwt_auth_users.router)
app.include_router(users_db.router)
app.mount("/static", StaticFiles(directory="static"), name="static")


# python -m uvicorn main:app --reload
# Swagger Doc: htto://localhost:8000/doc
# Redocly Doc: htto://localhost:8000/redoc


# URL: htto://localhost:8000
@app.get("/")
async def root():
    return {"message": "Hola FastAPI"}


# URL: htto://localhost:8000/url
@app.get("/url")
async def url():
    return {"message": "https://github.com/ManuelRomanFernandez"}
