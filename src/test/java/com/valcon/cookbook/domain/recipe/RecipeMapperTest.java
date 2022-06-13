//package com.valcon.cookbook.domain.recipe;
//
//import static nl.servicehouse.aureus.domain.payment.method.BasicPaymentMethod.PaymentMethod.IDEAL;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import nl.servicehouse.aureus.common.mapper.DescriptionMapper;
//import nl.servicehouse.aureus.web.dto.BasicPaymentMethodDto;
//
//@ExtendWith(MockitoExtension.class)
//class RecipeMapperTest {
//
//    @InjectMocks
//    private BasicPaymentMethodMapper basicPaymentMethodMapper;
//
//    @Mock
//    private DescriptionMapper descriptionMapper;
//
//    @Test
//    void mapToEntityTest() {
//        BasicPaymentMethodDto bpmDto = BasicPaymentMethodDto.builder()
//                                                            .method(IDEAL)
//                                                            .comment("Comment")
//                                                            .immediateExecution(true)
//                                                            .immediateSettlement(true)
//                                                            .canBeRefunded(true)
//                                                            .canBeReversed(true)
//                                                            .build();
//        BasicPaymentMethod bpm = basicPaymentMethodMapper.map(bpmDto);
//        assertThat(bpm.getMethod()).isEqualTo(IDEAL);
//        assertThat(bpm.getComment()).isEqualTo("Comment");
//        assertThat(bpm.getImmediateExecution()).isTrue();
//        assertThat(bpm.getImmediateSettlement()).isTrue();
//        assertThat(bpm.getCanBeRefunded()).isTrue();
//        assertThat(bpm.getCanBeReversed()).isTrue();
//    }
//
//    @Test
//    void mapToDtoTest() {
//        BasicPaymentMethod bpm = BasicPaymentMethod.builder()
//                                                      .id(1L)
//                                                      .method(IDEAL)
//                                                      .comment("Comment")
//                                                      .immediateExecution(true)
//                                                      .immediateSettlement(true)
//                                                      .canBeRefunded(true)
//                                                      .canBeReversed(true)
//                                                      .build();
//        BasicPaymentMethodDto bpmDto = basicPaymentMethodMapper.map(bpm);
//        assertThat(bpmDto.method()).isEqualTo(IDEAL);
//        assertThat(bpmDto.comment()).isEqualTo("Comment");
//        assertThat(bpmDto.immediateExecution()).isTrue();
//        assertThat(bpmDto.immediateSettlement()).isTrue();
//        assertThat(bpmDto.canBeRefunded()).isTrue();
//        assertThat(bpmDto.canBeReversed()).isTrue();
//    }
//}
