package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.domain.Recipe;
import com.springframework.recipeapp.services.RecipeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;
    AutoCloseable closeable;

    @Before
    public void setUp() throws Exception {
        closeable = MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        //given
        Set<Recipe> recipes = new HashSet<>();

        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipes.add(recipe1);

        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipes.add(recipe2);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String actual = indexController.getIndexPage(model);

        //then
        String expected = "index";
        String attribute = "recipes";

        assertEquals(expected, actual);
        verify(model, times(1)).addAttribute(eq(attribute), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}
