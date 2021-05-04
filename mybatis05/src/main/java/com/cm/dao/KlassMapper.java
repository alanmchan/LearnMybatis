package com.cm.dao;

import com.cm.pojo.Klass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KlassMapper {
    List<Klass> getKlass();

    Klass getKlassById(@Param("kid") int kid);

    Klass getKlassById2(@Param("kid") int kid);
}
