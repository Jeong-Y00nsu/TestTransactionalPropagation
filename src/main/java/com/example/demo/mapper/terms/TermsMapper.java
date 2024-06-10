package com.example.demo.mapper.terms;

import com.example.demo.domain.Term;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TermsMapper {

    int insertTerm(@Param("term")Term term);

    int deleteTerm(@Param("userId")String userId);

    List<Term> selectUserTermsByUserId(@Param("userId")String userId);

    List<Term> selectAllTerms();

    int deleteAllTerms();
}
