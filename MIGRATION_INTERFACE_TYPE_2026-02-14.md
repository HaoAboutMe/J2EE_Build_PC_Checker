# Migration: HddInterface & SsdInterface → InterfaceType

## Ngày thực hiện: 14/02/2026

## Tổng quan
Đã chuyển đổi thành công 2 entity `HddInterface` và `SsdInterface` thành một entity duy nhất `InterfaceType` để tối ưu hóa cấu trúc dữ liệu.

## Các file đã tạo mới

### 1. Entity
- `InterfaceType.java` - Entity chung cho cả HDD và SSD interface

### 2. Repository
- `InterfaceTypeRepository.java` - Repository cho InterfaceType

### 3. DTOs
- `InterfaceTypeRequest.java` - Request DTO
- `InterfaceTypeResponse.java` - Response DTO

### 4. Mapper
- `InterfaceTypeMapper.java` - MapStruct mapper

### 5. Service
- `InterfaceTypeService.java` - Service layer với đầy đủ CRUD operations

### 6. Controller
- `InterfaceTypeController.java` - REST API endpoints:
  - POST /interface-types
  - GET /interface-types
  - GET /interface-types/{id}
  - PUT /interface-types/{id}
  - DELETE /interface-types/{id}

## Các file đã cập nhật

### Entities
- `Hdd.java` - Thay `HddInterface hddInterface` → `InterfaceType interfaceType`
- `Ssd.java` - Thay `SsdInterface ssdInterface` → `InterfaceType interfaceType`

### Services
- `HddService.java` - Sử dụng `InterfaceTypeRepository` thay vì `HddInterfaceRepository`
- `SsdService.java` - Sử dụng `InterfaceTypeRepository` thay vì `SsdInterfaceRepository`

### DTOs
- `HddCreationRequest.java` - Thay `hddInterfaceId` → `interfaceTypeId`
- `HddUpdateRequest.java` - Thay `hddInterfaceId` → `interfaceTypeId`
- `SsdCreationRequest.java` - Thay `ssdInterfaceId` → `interfaceTypeId`
- `SsdUpdateRequest.java` - Thay `ssdInterfaceId` → `interfaceTypeId`
- `HddResponse.java` - Thay `HddInterfaceResponse` → `InterfaceTypeResponse`
- `SsdResponse.java` - Thay `SsdInterfaceResponse` → `InterfaceTypeResponse`

### Mappers
- `HddMapper.java` - Cập nhật mapping cho `interfaceType`
- `SsdMapper.java` - Cập nhật mapping cho `interfaceType`

### Error Codes
- `ErrorCode.java` - Thêm các error codes cho InterfaceType:
  - INTERFACE_TYPE_ENTITY_ID_REQUIRED (3121)
  - INTERFACE_TYPE_NAME_REQUIRED (3122)
  - INTERFACE_TYPE_NOT_FOUND (3123)

## Các file đã xóa

### HddInterface
- ❌ HddInterfaceService.java
- ❌ HddInterfaceController.java
- ❌ HddInterfaceMapper.java
- ❌ HddInterface.java (entity)
- ❌ HddInterfaceRepository.java
- ❌ HddInterfaceRequest.java
- ❌ HddInterfaceResponse.java

### SsdInterface
- ❌ SsdInterfaceService.java
- ❌ SsdInterfaceController.java
- ❌ SsdInterfaceMapper.java
- ❌ SsdInterface.java (entity)
- ❌ SsdInterfaceRepository.java
- ❌ SsdInterfaceRequest.java
- ❌ SsdInterfaceResponse.java

## Cấu trúc InterfaceType

```java
@Entity
@Table(name = "interface_type")
public class InterfaceType {
    @Id
    String id;        // SATA_3, SAS, PCIE_3, PCIE_4, PCIE_5
    String name;      // SATA III, SAS, PCIe 3.0 x4, PCIe 4.0 x4, PCIe 5.0 x4
}
```

## Cơ sở dữ liệu cần cập nhật

### 1. Tạo bảng mới
```sql
CREATE TABLE interface_type (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
```

### 2. Migrate dữ liệu
```sql
-- Chuyển dữ liệu từ hdd_interface
INSERT INTO interface_type (id, name)
SELECT id, name FROM hdd_interface;

-- Chuyển dữ liệu từ ssd_interface (không trùng)
INSERT INTO interface_type (id, name)
SELECT id, name FROM ssd_interface
WHERE id NOT IN (SELECT id FROM interface_type);
```

### 3. Cập nhật bảng HDD
```sql
-- Rename column
ALTER TABLE hdd 
RENAME COLUMN hdd_interface_id TO interface_type_id;
```

### 4. Cập nhật bảng SSD
```sql
-- Rename column
ALTER TABLE ssd 
RENAME COLUMN ssd_interface_id TO interface_type_id;
```

### 5. Xóa bảng cũ
```sql
DROP TABLE hdd_interface;
DROP TABLE ssd_interface;
```

## Kết quả

✅ Build thành công với Maven
✅ Không còn duplicate code giữa HddInterface và SsdInterface
✅ API endpoints đã được cập nhật
✅ Service layer hoạt động đúng với InterfaceType
✅ Cấu trúc code rõ ràng và dễ maintain hơn

## Lưu ý
- Cần chạy migration script cho database trước khi deploy
- API endpoints cũ cho HddInterface và SsdInterface đã bị xóa
- Sử dụng endpoint mới: `/interface-types`
- Cần cập nhật documentation và Postman collection

