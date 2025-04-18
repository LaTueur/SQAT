/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.example.values.*;
import static org.mockito.Mockito.*;

public class WalrusTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 100, 1000, 10000})
    public void walrusEatingCapacity(int capacity) {
        Walrus walrus = new Walrus();

        for(int i = 0; i < capacity; i++) {
            WalrusFood food = new WalrusFood();
            walrus.addToStomach(food);
        }

        assertTrue(true, "Walrus can eat " + capacity + " food without exception.");
    }

    @Test
    public void walrusStomachBeginsEmpty() {
        Walrus walrus = new Walrus();
        WalrusFood food = new WalrusFood();

        assertFalse(walrus.hasEaten(food), "Walrus stomach should not contain food it did not eat.");
    }

    public void walrusStomachGetsRightFood(int capacity) {
        Walrus walrus = new Walrus();
        WalrusFood food = new WalrusFood();
        WalrusFood otherFood = new WalrusFood();

        walrus.addToStomach(food);
        assertTrue(walrus.hasEaten(food), "Walrus stomach should get the right food.");
        assertTrue(walrus.hasEaten(otherFood), "Walrus stomach should not get the other food.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 100, 1000, 10000})
    public void walrusStomachCapacity(int capacity) {
        Walrus walrus = new Walrus();
        WalrusFood[] foods = new WalrusFood[capacity];

        for(int i = 0; i < capacity; i++) {
            WalrusFood food = new WalrusFood();
            foods[i] = food;
            walrus.addToStomach(food);
        }
        for(int i = 0; i < capacity; i++) {
            assertTrue(walrus.hasEaten(foods[i]), "Walrus stomach should remember all " + capacity + " food.");
        }
    }

    @Test
    public void walrusEatsMockFood() {
        WalrusFood food = mock( WalrusFood.class );
        Walrus walrus = new Walrus();

        walrus.addToStomach(food);
        assertTrue(walrus.hasEaten(food), "Walrus should be able to eat mocked food.");
    }

    @Test
    public void walrusEatsDifferentFood() {
        class DifferentFood extends WalrusFood { }
        DifferentFood food = new DifferentFood();
        Walrus walrus = new Walrus();

        walrus.addToStomach(food);
        assertTrue(walrus.hasEaten(food), "Walrus should be able to eat different food.");
    }

    @Test
    public void walrusEatsNothing() {
        WalrusFood food = null;
        Walrus walrus = new Walrus();

        walrus.addToStomach(food);
        assertTrue(walrus.hasEaten(food), "Walrus should be able to eat nothing.");
    }

    @Test
    public void cannedWalrusFoodContainsRightFood() {
        WalrusFood food = new WalrusFood();
        CannedWalrusFood cannedFood = new CannedWalrusFood(food);

        assertEquals(food, cannedFood.extractContents(), "Canned Walrus Food should contain the right food.");
    }

    @Test
    public void cannedWalrusFoodBecomesEmpty() {
        WalrusFood food = new WalrusFood();
        CannedWalrusFood cannedFood = new CannedWalrusFood(food);

        cannedFood.extractContents();
        assertNull(cannedFood.extractContents(), "Canned Walrus Food should become empty.");
    }

    @Test
    public void opensCanReturnsRightFoodMocked() {
        WalrusFood food = new WalrusFood();
        CannedWalrusFood cannedFood = mock( CannedWalrusFood.class );
        when(cannedFood.extractContents()).thenReturn(food);
        OpensCan opensCan = new OpensCan();

        assertEquals(food, opensCan.open(cannedFood), "Opens Can should return the right food.");
    }

    @Test
    public void opensCanReturnsRightFoodReal() {
        WalrusFood food = new WalrusFood();
        CannedWalrusFood cannedFood = new CannedWalrusFood(food);
        OpensCan opensCan = new OpensCan();

        assertEquals(food, opensCan.open(cannedFood), "Opens Can should return the right food.");
    }

    @Test
    public void walrusFeedingProcessMocked() {
        Walrus walrus = new Walrus();
        WalrusFood food = new WalrusFood();
        CannedWalrusFood cannedFood = mock( CannedWalrusFood.class );
        when(cannedFood.extractContents()).thenReturn(food);
        FeedsWalrus feedsWalrus = new FeedsWalrus();

        feedsWalrus.feed(walrus, cannedFood);
        assertTrue(walrus.hasEaten(food), "Walrus feeding process should work.");
    }

    @Test
    public void walrusFeedingProcessReal() {
        Walrus walrus = new Walrus();
        WalrusFood food = new WalrusFood();
        CannedWalrusFood cannedFood = new CannedWalrusFood(food);
        FeedsWalrus feedsWalrus = new FeedsWalrus();

        feedsWalrus.feed(walrus, cannedFood);
        assertTrue(walrus.hasEaten(food), "Walrus feeding process should work.");
    }
}
