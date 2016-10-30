package com.tw.harper.model;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
public class BookTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @Test
    public void test(){
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
    @Test
    public void test1(){
        LinkedList mockedList = mock(LinkedList.class);
        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());
        doThrow(new RuntimeException()).when(mockedList).clear();
        System.out.println(mockedList.get(0));
//        mockedList.clear();
//        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(999));
        verify(mockedList).get(0);

        when(mockedList.get(anyInt())).thenReturn("element");

//        when(mockedList.contains(argThat(isVaild())))
    }
    @Test
    public void test2(){
        LinkedList mockedList = mock(LinkedList.class);
        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
//        verify(mockedList, atLeast(2)).add("five times");
        verify(mockedList, atMost(5)).add("three times");
    }
    @Test
    public void testVerificationInOrder(){
        List singleMock = mock(List.class);
        singleMock.add("first");
        singleMock.add("second");
        InOrder inOrder = inOrder(singleMock);
        inOrder.verify(singleMock).add("first");
        inOrder.verify(singleMock).add("second");

        List firstMock = mock(List.class);
        List secondMock = mock(List.class);
        firstMock.add("first");
        secondMock.add("second");
        InOrder inOrder1 = inOrder(firstMock,secondMock);
        inOrder1.verify(firstMock).add("first");
        inOrder1.verify(secondMock).add("second");
    }

    @Test
    public void testCallBacks(){
        Some mock = mock(Some.class);
        when(mock.doSomeThing(anyString())).thenAnswer(new Answer(){
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "call with arguments:" + args;
            }
        });
        System.out.println("foo");
    }

    @Test
    public void testSpy(){
        List list = new LinkedList<>();
        List spy = spy(list);
        System.out.println(list.hashCode());
        System.out.println(spy.hashCode());
        when(spy.size()).thenReturn(100);
        spy.add("one");
        spy.add("two");
        when(spy.get(1)).thenReturn("foo");
        System.out.println(spy.get(0));
        System.out.println(spy.size());
        verify(spy).add("one");
        verify(spy).add("two");
        doReturn("foo").when(spy).get(2);

        System.out.println(spy.size());
        System.out.println(list.size());


    }

    @Test
    public void testCaptureArgs(){
        Some mock = mock(Some.class);
        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        verify(mock).doSomeThing(argument.capture());
        assertEquals("John",argument.getValue().getName());
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }
}
class Some{
    public String doSomeThing(String value){
        return value;
    }
    public String doSomeThing(Person person){
        return person.getName();
    }
}
class Person{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
