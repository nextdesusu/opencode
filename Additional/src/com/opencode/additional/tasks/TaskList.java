package com.opencode.additional.tasks;

public class TaskList <T> {
    // Начальный размер массива
    private static int DEFAULT_SIZE = 3;

    private T[] container;

    private int length = 0;

    public TaskList() {
        container = (T[]) new Object[DEFAULT_SIZE];
    }

    public TaskList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    public int getLength() {
        return length;
    }

    public int getCapacity() {
        return container.length;
    }

    /**
     * Добавляет элемент в конец списка
      * @param el добавляемый элемент
     */
    public void add(T el) {
        maybeGrow();
        container[length] = el;
        length++;
    }

    /**
     * Вставляет элемент в любое место массива
     * @param index место вставки
     * @param el элемент для вставки
     */
    public void insertAt(int index, T el) {
        maybeThrow(index);
        maybeGrow();
        for (int i = length - 1; i > index - 1; i--) {
            container[i + 1] = container[i];
        }
        container[index] = el;
        length++;
    }

    /**
     * Удаляет элемент по индексу
     * @param index место удаления
     */
    public void removeAt(int index) {
        maybeThrow(index);
        for (int i = index + 1; i < length; i++) {
            container[i - 1] = container[i];
        }
        length--;
    }

    /**
     * Печатает список в виде строки
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * Приводит список в строку
     * @return список в виде строки
     */
    public String toString() {
        String current = "[";
        for (int i = 0; i < length; i++) {
            if (i == length - 1) {
                current += container[i];
            } else {
                current += container[i] + ", ";
            }
        }
        return current + "]";
    }

    /**
     * Приводит список в отсортированный вид
     */
    public void sort(Comparator<T> cmp) {
        doSort(0, length - 1, cmp);
    }

    /**
     * Quicksort
     * @param left левая граница
     * @param right правая граница
     * @param cmp компаратор
     */
    private void doSort(int left, int right, Comparator<T> cmp) {
        int i = left;
        int j = right;
        int middleIndex = (left + right) / 2;
        T middleElem = container[middleIndex];
        while (i <= j) {
            for (;cmp.compare(container[i], middleElem) == Ordering.LESSER; i++) {
                if (i == length - 1) {
                    break;
                }
            };

            for (;cmp.compare(container[j], middleElem) == Ordering.GREATER; j--) {
                if (j == 0) {
                    break;
                }
            };

            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if (left < j) {
            doSort(left, j, cmp);
        }
        if (i < right) {
            doSort(i, right, cmp);
        }
    }

    /**
     * Возвращает элемент по индексу
     * @param index индекс элемента может быть отрицательным
     * @return элемент по индексу
     */
    public T at(int index) {
        int computed = index;
        if (index < 0) {
            computed = length - computed;
        }
        maybeThrow(computed);
        return container[computed];
    }

    /**
     * Очищает список
     */
    public void clear() {
        for (int i = 0; i < length; i++){
            // Удаляем ссылки на элементы
            container[i] = null;
        }
        length = 0;
    }

    /**
     * Меняет два элемента местами
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    public void swap(int i, int j) {
        maybeThrow(i);
        maybeThrow(j);
        T tmp = container[i];
        container[i] = container[j];
        container[j] = tmp;
    }

    /**
     * Метод для сравнения списков
     * @param obj список для сравнения
     * @return равенство списков
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof TaskList)) {
            return false;
        }

        TaskList<Object> castedObj = (TaskList<Object>) obj;
        if (castedObj.getLength() != getLength()) {
            return false;
        }

        for (int i = 0; i < getLength(); i++) {
            if (!container[i].equals(castedObj.at(i))) {
                return false;
            }
        }

        return true;
    }

    private void maybeThrow(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void maybeGrow() {
        if (length == getCapacity()) {
            T[] newContainer = (T[]) new Object[getCapacity() * 2];

            for (int i = 0; i < length; i++) {
                newContainer[i] = container[i];
            }

            container = newContainer;
        }
    }

    public enum Ordering {
        LESSER,
        EQUAL,
        GREATER;

        public static Ordering compareIntegers(int a, int b) {
            if (a > b) {
                return GREATER;
            }
            if (a < b) {
                return LESSER;
            }
            return EQUAL;
        }
    }

    public interface Comparator <T> {
        Ordering compare(T a, T b);
    }
}
