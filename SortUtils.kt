import java.util.*


/**
 * Utilities used for sorting algorithm
 **/

/**
 * enum for sorting algorithm
 */
enum class Sort {
    BUBBLE,
    SELECTION,
    INSERTION,
    MERGE,
    QUICK
}

/**
 * perform sort
 *
 * @param array unsorted array
 * @param algorithm sorting algorithm to be used
 * @return sorted array
 */
fun performSort(array: Array<Int>, algorithm: Sort): Array<Int> {
    if (array.isEmpty()) {
        throw ArrayIndexOutOfBoundsException("Array should not be empty")
    }

    System.out.println("\nAlgorithm : ${algorithm.name}")
    System.out.println("Unsorted Array : ${Arrays.toString(array)}")
    return when (algorithm) {
        Sort.BUBBLE -> {
            val sortedArray = bubbleSort(array)
            System.out.println("Sorted array : ${Arrays.toString(sortedArray)}")
            sortedArray
        }
        Sort.SELECTION -> {
            val sortedArray = selectionSort(array)
            System.out.println("Sorted array : ${Arrays.toString(sortedArray)}")
            sortedArray
        }
        Sort.INSERTION -> {
            val sortedArray = insertionSort(array)
            System.out.println("Sorted array : ${Arrays.toString(sortedArray)}")
            sortedArray
        }
        Sort.MERGE -> {
            val sortedArray = mergeSort(array)
            System.out.println("Sorted array : ${Arrays.toString(sortedArray)}")
            sortedArray
        }
        Sort.QUICK -> {
            val sortedArray = quickSort(array)
            System.out.println("Sorted array : ${Arrays.toString(sortedArray)}")
            sortedArray
        }
    }
}

/**
 * perform bubble sort
 *
 * @param array unsorted array
 * @return sorted array
 */
private fun bubbleSort(array: Array<Int>): Array<Int> {
    val size = array.size - 1
    var temp: Int

    for (i in 0 until size) {
        var isSwap = false
        for (j in 0 until size - i) {
            if (array[j] > array[j + 1]) {
                temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
                isSwap = true
            }
        }

        if (!isSwap) {
            break
        }

    }
    return array
}

/**
 * perform selection sort
 *
 * @param array unsorted array
 * @return sorted array
 */
private fun selectionSort(array: Array<Int>): Array<Int> {
    val size = array.size
    var temp: Int

    for (i in 0 until size) {
        var minIndex = i
        for (j in i + 1 until size) {
            if (array[j] < array[minIndex]) {
                minIndex = j
            }
        }

        temp = array[minIndex]
        array[minIndex] = array[i]
        array[i] = temp

    }
    return array
}

/**
 * perform insertion sort
 *
 * @param array unsorted array
 * @return sorted array
 */
private fun insertionSort(array: Array<Int>): Array<Int> {
    val size = array.size
    for (i in 1 until size) {
        val key = array[i]
        var j = i - 1

        while (j >= 0 && array[j] > key) {
            array[j + 1] = array[j]
            j -= 1
        }
        array[j + 1] = key
    }
    return array
}

/**
 * perform merge sort
 *
 * @param array unsorted array
 * @return sorted array
 */
private fun mergeSort(array: Array<Int>, left: Int = 0, right: Int = array.size - 1): Array<Int> {
    if (left < right) {
        val middle = (left + right) / 2
        mergeSort(array, left, middle)
        mergeSort(array, middle + 1, right)
        mergeArrays(array, left, middle, right)
    }
    return array
}

/**
 * merge sorted arrays
 *
 * @param array array to be merged
 * @param left left index of array
 * @param middle middle of array
 * @param right right index of array
 *
 */
fun mergeArrays(array: Array<Int>, left: Int, middle: Int, right: Int) {
    // find sizes of spliced array
    val leftArraySize = middle - left + 1
    val rightArraySize = right - middle

    // create empty array of size
    val leftArray = IntArray(leftArraySize)
    val rightArray = IntArray(rightArraySize)

    // copy element of size
    for (i in 0 until leftArraySize) {
        leftArray[i] = array[left + i]
    }

    for (j in 0 until rightArraySize) {
        rightArray[j] = array[middle + j + 1]
    }

    var i = 0
    var j = 0
    var k = left

    while (i < leftArraySize && j < rightArraySize) {
        // check left array element is less
        if (leftArray[i] <= rightArray[j]) {
            array[k] = leftArray[i]
            i++
        } else {
            array[k] = rightArray[j]
            j++
        }
        k++
    }

    // copy remaining element to left array
    while (i < leftArraySize) {
        array[k] = leftArray[i]
        k++
        i++
    }

    // copy remaining element to right array
    while (j < rightArraySize) {
        array[k] = rightArray[j]
        k++
        j++
    }
}

/**
 * perform quick sort
 *
 * @param array unsorted array
 * @return sorted array
 */
private fun quickSort(array: Array<Int>, left: Int = 0, right: Int = array.size - 1): Array<Int> {
    if (left < right) {
        val pivot = getPartitionIndex(array, left, right)
        quickSort(array, left, pivot - 1)
        quickSort(array, pivot + 1, right)
    }
    return array
}

/**
 * get partition index
 *
 * @param array unsorted array
 * @param left index
 * @param right index
 * @return partition index
 */
fun getPartitionIndex(array: Array<Int>, left: Int, right: Int): Int {
    //considering right as pivot
    val pivot = array[right]
    var i = left - 1
    var temp: Int

    for (j in left until right) {
        if (array[j] <= pivot) {
            i++
            temp = array[i]
            array[i] = array[j]
            array[j] = temp
        }
    }

    temp = array[i + 1]
    array[i + 1] = array[right]
    array[right] = temp
    return i + 1
}
