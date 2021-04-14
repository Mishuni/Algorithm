import java.util.Arrays;

public class Heap {
	// 배열을 이용한 max heap 의 구현
	// 왼쪽 자식 노드의 index = (부모 노드 인덱스) * 2
	// 오른쪽 자식 노드의 index = (부모 노드 인덱스) * 2 + 1
	// 부모 노드의 index = 자식 노드 인덱스 / 2
	public static final int MAX = 200;
	private int[] heap;
	private int heap_size;
	
	Heap(){
		heap = new int[MAX+1];
		heap_size = 0;
	}
	
	public int[] getHeap() {
		return heap;
	}

	public int getHeap_size() {
		return heap_size;
	}
	
	public void insert(int item) {
		if(heap_size<MAX){
			int i = ++heap_size; // root
			while((i!=1) && (heap[i/2] < item)) {
				// 부모 노드 값을 밑으로 내리기
				heap[i] = heap[i/2];
				i /= 2;
			}
			heap[i]=item;
		}
	}
	
	public int delete() {
		// root 값 삭제
		int root = heap[1];
		
		// 마지막 값을 root 로 보내고 사이즈 줄이기
		heap[1] = heap[heap_size];
		heap[heap_size--]=0;
		// root 값 다시 찾기
		int i = 1;
		int start = 1;
		while(i<=Math.sqrt(heap_size)) {
			int a = heap[start*2];
			int b = heap[start*2+1];
			int index = (a>b)? start*2 : start*2+1;
			int max = (a>b)? a : b;
			if(max > heap[start]) {
				int tmp = heap[start];
				heap[start]= max;
				heap[index]=tmp;
				start = index;
			}else {
				break;
			}
			++i;

		}
		return root;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Heap [heap=" + Arrays.toString(heap) + ", heap_size=" + heap_size + "]";
	}

	public static void main(String[] args) {
		Heap h1 = new Heap();
		h1.insert(3);
		h1.insert(10);
		h1.insert(5);
		h1.insert(1);
		h1.insert(20);
		System.out.println(h1);
		h1.delete();
		System.out.println(h1);
	}

}
