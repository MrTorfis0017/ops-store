package com.ops.admin.services;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.converters.SupplyConverter;
import com.ops.admin.entities.Supply;
import com.ops.admin.repositories.SupplyItemRepository;
import com.ops.admin.repositories.SupplyRepository;
import com.ops.common.dto.SupplyDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

import static com.ops.admin.utils.GetTestData.getSupply;
import static com.ops.admin.utils.GetTestData.getSupplyDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, SupplyService.class})
public class SupplyServiceTest {

    @InjectMocks
    private SupplyService supplyService;

    @Mock
    private SupplyConverter supplyConverter;

    @Mock
    private SupplyRepository supplyRepository;

    @Mock
    private SupplyItemRepository supplyItemRepository;

    @Test
    public void createSupplyTest() {
        Supply supply = getSupply();
        SupplyDTO supplyDTO = getSupplyDTO();

        when(supplyConverter.fromDTO(any())).thenReturn(supply);
        when(supplyRepository.save(any())).thenReturn(supply);
        when(supplyRepository.getReferenceById(any())).thenReturn(supply);
        when(supplyConverter.toDTO(any())).thenReturn(supplyDTO);
        when(supplyItemRepository.saveAll(any())).thenReturn(new ArrayList<>());

        SupplyDTO result = supplyService.create(supplyDTO);

        assertEquals(supplyDTO, result);
    }
}
