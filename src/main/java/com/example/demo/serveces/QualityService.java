package com.example.demo.serveces;

import com.example.demo.model.Quality;
import com.example.demo.repositries.QualityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QualityService {
    private final QualityRepo qualityRepo;
    @Autowired
    public QualityService(QualityRepo qualityRepo) {
        this.qualityRepo = qualityRepo;
    }

    public List<Quality> getAll() {
        return qualityRepo.findAll();
    }

    public Quality getById(Integer id) {
        Optional<Quality> quality=qualityRepo.findById(id);
        if(!quality.isPresent()){
            throw new IllegalStateException("there is no record");

        }
        return quality.get();
    }

    public Double getPercentageOfDefect() {
        Integer numDefect=qualityRepo.findCountOfDefect();
        Integer numOk=qualityRepo.findCountOfOk();
        Double percent= Double.valueOf(numDefect/(numDefect+numOk));
        return percent;

    }
    public Double getPercentageOfOk() {
        Integer numDefect=qualityRepo.findCountOfDefect();
        Integer numOk=qualityRepo.findCountOfOk();
        Double percent= Double.valueOf(numOk/(numDefect+numOk));
        return percent;

    }

    public Double getPercentageOfDefectById(Integer id) {
        Quality quality=getById(id);
        Integer numDefect=quality.getNumDefect();
        Integer numOk=quality.getNumOk();
        Double percent= Double.valueOf(numDefect/(numDefect+numOk));
        return percent;
    }
    public Double getPercentageOfOkById(Integer id) {
        Quality quality=getById(id);
        Integer numDefect=quality.getNumDefect();
        Integer numOk=quality.getNumOk();
        Double percent= Double.valueOf(numOk/(numDefect+numOk));
        return percent;
    }
    @Transactional
    public void activate(Integer id) {
        Quality quality=getById(id);
        qualityRepo.setAllActivateFalse();
        quality.setActivate(true);
        qualityRepo.save(quality);
    }
    public Quality getByActivate(Boolean x){
        return qualityRepo.getByActivate(x);
    }
}
