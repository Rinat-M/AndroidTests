package com.geekbrains.tests

import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.*
import org.junit.Test

class DetailsPresenterTest {

    @Test
    fun test_SetCounter_Success() {
        val presenter = DetailsPresenter()
        presenter.setCounter(5)
        assertEquals(5, presenter.getCounter())
    }

    @Test
    fun test_OnIncrement_Success() {
        val presenter = DetailsPresenter().apply {
            onIncrement()
        }
        assertEquals(1, presenter.getCounter())
    }

    @Test
    fun test_OnDecrement_Success() {
        val presenter = DetailsPresenter().apply {
            setCounter(3)
            onDecrement()
        }
        assertEquals(2, presenter.getCounter())
    }

    @Test
    fun test_OnAttach_IsInvoked() {
        val view = mock<ViewDetailsContract>()
        val presenter = DetailsPresenter().apply {
            onAttach(view)
        }
        assertTrue(presenter.viewIsAttached)
    }

    @Test
    fun test_OnDetach_IsInvoked() {
        val view = mock<ViewDetailsContract>()
        val presenter = DetailsPresenter().apply {
            onAttach(view)
            onDetach()
        }
        assertFalse(presenter.viewIsAttached)
    }
}