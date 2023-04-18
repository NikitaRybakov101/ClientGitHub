package com.example.clientgithub.repositoryImpl;

import com.example.clientgithub.dataSource.ResponseMy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface InterfaceRepository {
    List<ResponseMy> getListPoison(String search, int offset, int pageSize) throws IOException;
    ResponseMy getPoison(String idPoison) throws IOException;
}
