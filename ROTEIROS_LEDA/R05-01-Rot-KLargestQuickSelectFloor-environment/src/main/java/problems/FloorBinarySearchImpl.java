package problems;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		boolean checkParameters = array.length > 0 && array != null;
		Integer idxFloor;
		if (checkParameters) {
			quickSort(array, 0, array.length - 1);
			idxFloor = this.findFloor(array, x, 0, array.length - 1);
			return array[idxFloor];
		}
		return null;
	}

	private Integer binarySearch(Integer[] array, Integer x, int ini, int fim) {
		Integer out = -1;

		if (ini < fim) {
			int meio = (ini + fim) / 2;

			if (array[meio] == x) {
				out = meio;
			} else if (x < array[meio]) {
				out = binarySearch(array, x, ini, meio - 1);
			} else {
				out = binarySearch(array, x, meio + 1, fim);
			}
		}

		return out;
	}

	private Integer findFloor(Integer[] array, Integer x, int ini, int fim) {
		Integer floor = this.binarySearch(array, x, ini, fim);
		if (floor == -1) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] < x && array[i] > floor)
					floor = i;
			}
		}
		return floor;
	}

	public void quickSort(Integer[] array, int leftIndex, int rightIndex) {
		boolean checkParameters = array.length > 0 && rightIndex <= array.length - 1 && leftIndex >= 0;
		if (checkParameters) {
			if (leftIndex < rightIndex) {
				int idxPivot = this.partition(array, leftIndex, rightIndex);
				this.quickSort(array, leftIndex, idxPivot - 1);
				this.quickSort(array, idxPivot + 1, rightIndex);
			}
		}
	}

	private int partition(Integer[] array, int leftIndex, int rightIndex) {
		Integer pivot = array[leftIndex];
		int i = leftIndex;

		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(pivot) <= 0) {
				swap(array, j, ++i);
			}
		}
		swap(array, leftIndex, i);

		return i;
	}

	public static void swap(Integer[] array, int i, int j) {
		Integer temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
