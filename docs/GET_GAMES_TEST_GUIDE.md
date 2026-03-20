# Huong Dan Test Endpoint GET /games

Tai lieu nay huong dan ban test endpoint `GET /identity/games` trong BuildPC Checker.

## 1) Muc tieu endpoint

Endpoint nay tra ve danh sach game theo dang phan trang va co the loc theo the loai.

- Path: `GET /identity/games`
- Query params:
  - `genre` (optional): loc theo the loai, vi du `FPS`, `RPG`, `Action`
  - `page` (optional, mac dinh `0`): so trang (bat dau tu 0)
  - `size` (optional, mac dinh `20`): so ban ghi moi trang

## 2) Cau truc response mong doi

Response duoc boc trong `ApiResponse`:

```json
{
  "code": 1000,
  "message": "Success",
  "result": {
    "content": [
      {
        "id": "uuid",
        "name": "Cyberpunk 2077",
        "genre": "RPG/Action",
        "coverImageUrl": "..."
      }
    ],
    "totalElements": 12,
    "totalPages": 1,
    "size": 20,
    "number": 0
  }
}
```

## 3) Test bang Swagger UI

1. Mo `http://localhost:8080/identity/swagger-ui.html`
2. Tim endpoint `GET /games`
3. Bam **Try it out**
4. Chay cac case ben duoi

### Case A - Mac dinh
- `genre`: de trong
- `page`: `0`
- `size`: `20`

Ky vong:
- HTTP `200`
- `result.content` co du lieu (neu da seed game)
- `result.number = 0`
- `result.size = 20`

### Case B - Gioi han 5 game/trang
- `genre`: de trong
- `page`: `0`
- `size`: `5`

Ky vong:
- HTTP `200`
- `result.content.length <= 5`
- `result.totalElements >= result.content.length`

### Case C - Trang tiep theo
- `genre`: de trong
- `page`: `1`
- `size`: `5`

Ky vong:
- HTTP `200`
- `result.number = 1`
- Neu tong du lieu > 5 thi noi dung trang 1 khac trang 0

### Case D - Loc theo genre ton tai
- `genre`: `FPS`
- `page`: `0`
- `size`: `20`

Ky vong:
- HTTP `200`
- Cac item trong `content` co genre phu hop voi filter

### Case E - Loc genre khong ton tai
- `genre`: `KHONG_TON_TAI`
- `page`: `0`
- `size`: `20`

Ky vong:
- HTTP `200`
- `result.content` la mang rong
- `result.totalElements = 0`

## 4) Test bang cURL

Neu he thong cua ban yeu cau token, them header `Authorization: Bearer <token>`.

### Lay trang dau tien
```bash
curl --location "http://localhost:8080/identity/games?page=0&size=20"
```

### Loc theo genre
```bash
curl --location "http://localhost:8080/identity/games?genre=FPS&page=0&size=10"
```

### Co Authorization header
```bash
curl --location "http://localhost:8080/identity/games?page=0&size=20" \
  --header "Authorization: Bearer <your_access_token>"
```

## 5) Cac diem can check ky

- `page` la 0-based
- `size` quyet dinh so item toi da trong `content`
- `totalElements` la tong so game sau khi da filter
- `totalPages` thay doi theo `size`
- `genre` la optional

## 6) Loi thuong gap

- Nhin nham endpoint:
  - `GET /games` la danh sach game
  - Endpoint FPS la `POST /games/{id}/estimate-fps`
- Chua co data game trong DB thi `content` rong
- Truyen `size` qua nho/qua lon lam ket qua kho doc

## 7) Checklist test toi thieu

- [ ] Case A
- [ ] Case B
- [ ] Case C
- [ ] Case D
- [ ] Case E

