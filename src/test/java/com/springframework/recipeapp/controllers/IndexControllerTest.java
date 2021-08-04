package com.springframework.recipeapp.controllers;

import com.springframework.recipeapp.domain.Recipe;
import com.springframework.recipeapp.services.RecipeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
    public void getIndexPage() {
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipeData);

        String expected = "index";
        String attribute = "recipes";
        String actual = indexController.getIndexPage(model);

        assertEquals(expected, actual);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(attribute, anySet());
    }
}
