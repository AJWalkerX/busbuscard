package com.ahmete.busbuscard.mapper;

import com.ahmete.busbuscard.dto.request.BankTransactionDto;
import com.ahmete.busbuscard.dto.request.MoneyTransactionDto;
import com.ahmete.busbuscard.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
	
	TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
	
	Transaction fromMoneyTransactionDto(final MoneyTransactionDto dto);
	
	Transaction fromBankTransactionDto(final BankTransactionDto dto);
}