### Soru.1-ismi eşleşmeyen fieldları birbirine nasıl eşlerim ?
MapStruct Kullanarak Eşleme
Eğer manuel eşleme yerine daha az kod yazmak istiyorsan, MapStruct gibi bir kütüphane kullanarak otomatik eşlemeler yapabilirsin. MapStruct, farklı field isimlerini belirttiğin zaman aralarındaki ilişkiyi kurabilir.

Örnek:

@Mapper
public interface UserMapper {
@Mapping(source = "firstName", target = "name")
@Mapping(source = "lastName", target = "surname")
User dtoToEntity(UserDTO dto);
}
* entity den gelen fieldlar target
* Dto dan gelen fiedlar source

### Soru.2-mapper kullanarak spring boot projesinde bir entitynin update işlemini gercekleştirmek(update sırasında sadece verdiğim veri gecerli olur diğer veriler null a duser bunu önlememiz lazım)

MapStruct ile Partial Update (Kısmi Güncelleme)
MapStruct kullanarak, sadece DTO'dan gelen non-null (null olmayan) alanların entity'ye kopyalanmasını sağlayabilirsin. Bunun için MapStruct'un @MappingTarget özelliğini kullanabilirsin.

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    void updateUserFromDto(UserDTO dto, @MappingTarget User entity);
}
* @MappingTarget anotasyonu, var olan entity'yi güncellemek istediğini belirtir.
* nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE ile null olan alanların güncellemeye dahil edilmesini önlersin.