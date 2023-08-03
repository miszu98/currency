package pl.xcodesoftware.nbp.mapper;

import org.mapstruct.Mapper;

import pl.xcodesoftware.nbp.dto.CurrencyRequestInfoDTO;
import pl.xcodesoftware.nbp.entity.CurrencyRequestInfoEntity;

@Mapper(componentModel = "spring")
public interface CurrencyRequestInfoMapper {
    CurrencyRequestInfoDTO currencyRequestInfoEntityToDto(CurrencyRequestInfoEntity currencyRequestInfoEntity);
}
