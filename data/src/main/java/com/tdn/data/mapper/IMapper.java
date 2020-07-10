package com.tdn.data.mapper;

public interface IMapper<From,To,String> {
    To map(From from, String id);
}
