package com.bankpin.user.terms.service;

import com.bankpin.user.terms.model.dto.TermsAgreeDTO;
import com.bankpin.user.terms.model.mapper.TermsAgreeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TermsService {

    private final TermsAgreeMapper termsAgreeMapper;

    public void create(TermsAgreeDTO.Create create) {
        termsAgreeMapper.save(create);
    }


    public void createEach(TermsAgreeDTO.Param parameter) {

        for (TermsAgreeDTO.Param.TermsAgree agree : parameter.getTermsTypes()) {

            TermsAgreeDTO.Create create = TermsAgreeDTO.Create.builder()
                    .userId(parameter.getUserId())
                    .termsType(agree.getTermsType())
                    .accept(agree.getAccept())
                    .build();

            termsAgreeMapper.save(create);

        }
    }

    public void upsertEach(TermsAgreeDTO.Param parameter) {

        for (TermsAgreeDTO.Param.TermsAgree agree : parameter.getTermsTypes()) {

            TermsAgreeDTO.Create create = TermsAgreeDTO.Create.builder()
                    .userId(parameter.getUserId())
                    .termsType(agree.getTermsType())
                    .accept(Objects.equals(agree.getAccept().toUpperCase(), "Y") ? "Y" : "N")
                    .build();

            if (termsAgreeMapper.existsByUserIdAndTermsType(create)) {
                termsAgreeMapper.update(create);
            } else {
                termsAgreeMapper.save(create);
            }

        }

    }


    public void deleteEach(TermsAgreeDTO.Param parameter) {

        for (TermsAgreeDTO.Param.TermsAgree agree : parameter.getTermsTypes()) {

            TermsAgreeDTO.Create create = TermsAgreeDTO.Create.builder()
                    .userId(parameter.getUserId())
                    .termsType(agree.getTermsType())
                    .build();

            termsAgreeMapper.deleteByIdAndTermsType(create);

        }

    }
}
