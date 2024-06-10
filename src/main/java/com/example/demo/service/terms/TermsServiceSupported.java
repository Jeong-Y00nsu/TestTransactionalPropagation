package com.example.demo.service.terms;

import com.example.demo.constant.Constant;
import com.example.demo.domain.Response;
import com.example.demo.domain.Term;
import com.example.demo.mapper.terms.TermsMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("termsServiceSupported")
public class TermsServiceSupported extends TermsService {
    public TermsServiceSupported(TermsMapper termsMapper) {
        super(termsMapper);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Response agreeTerms(List<Term> agreeTerms, boolean isException){
        for(Term agreeTerm : agreeTerms){
            termsMapper.insertTerm(agreeTerm);
        }
        if(isException) throw new RuntimeException();
        return new Response(Constant.OK,"성공");
    }
}
