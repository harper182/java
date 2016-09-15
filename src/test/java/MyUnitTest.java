
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
public class MyUnitTest {
    public void test(){
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();
        verify(mockedList).add("one");
        verify(mockedList).clear();
        LinkedList mockedList1 = mock(LinkedList.class);
        when(mockedList1.get(0)).thenReturn("first");
        when(mockedList1.get(1)).thenReturn(new RuntimeException());
        verify(mockedList1.get(0));
    }

}
