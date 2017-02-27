package com.ciazhar.dao;

import com.ciazhar.model.PesertaPaging;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ciazhar on 2/26/17.
 */

public interface PesertaPagingDao extends PagingAndSortingRepository<PesertaPaging, String>{

}
