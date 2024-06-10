package com.example.demo.service.terms;

import com.example.demo.domain.Response;
import com.example.demo.domain.Term;
import com.example.demo.mapper.terms.TermsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class TermsService {
    TermsMapper termsMapper;

    @Autowired
    public TermsService(TermsMapper termsMapper){
        this.termsMapper = termsMapper;
    }

    public abstract Response agreeTerms(List<Term> agreeTerms, boolean isException);

}
