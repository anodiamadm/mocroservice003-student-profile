package com.anodiam.CRUDStudentProfile.serviceRepository.address.Suburb;

import com.anodiam.CRUDStudentProfile.model.address.Suburb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
class SuburbServiceDal extends SuburbServiceImpl {

    @Autowired
    private SuburbService suburbService;

    public SuburbServiceDal(){}

    @Override
    public List<Suburb> findByTownId(BigInteger townId) {
        try {
            List<Suburb> suburbs = suburbService.findByTownId(townId);
            if(!suburbs.isEmpty()) {
                return suburbs;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
