package ch.heigvd.amt.stack.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.enterprise.inject.se.SeContainerInitializer;

import static org.junit.jupiter.api.Assertions.*;

class ServiceRegistryTest {

    static SeContainerInitializer initializer;

    @BeforeAll
    public static void setupCdi(){
        initializer = SeContainerInitializer.newInstance();
    }

    @Test
    void getIdentityManagementFacade() {
    }

    @Test
    void getQuestionFacade() {
    }

    @Test
    void getAnswerFacade() {
    }

    @Test
    void getCommentFacade() {
    }
}