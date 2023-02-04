package ukr.stas.taskmanager.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ukr.stas.taskmanager.service.TaskService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AssigmentControllerTest {
    @InjectMocks
    AssigmentController assigmentController = new AssigmentController();
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(assigmentController).build();
    }

    @Test
    public void index_shouldReturnStatusOkAndAssignmentAsViewName() throws Exception {
        mockMvc.perform(get("/assignment"))
                .andExpect(status().isOk())
                .andExpect(view().name("assignment"));
    }

}
