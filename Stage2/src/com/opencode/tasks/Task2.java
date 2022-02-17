package tasks;

public class Task2 implements Runnable {
    @Override
    public void run() {
        String input = TaskUtil.getUserInput("Введите символы");
//         Оставляет только русские и английские буквы
        System.out.println("Ввод: " + leaveOnlyLettersAndWhiteSpaces(input));

//         В качестве алфавита будут русские буквы
//         В качестве других символов пусть будут цифры
//        System.out.println("Ввод " + input.replaceAll("\\d", ""));

//        для тестов
//        System.out.println(leaveOnlyLettersAndWhiteSpaces("as/fa f\\уца|уЯц34faf ф234/.cas.у1а`ф //fw2#$12~@ef'fw fsу\"ыаа345 dsfsf.wfe/ew*?f:№2342:4    e;wf-efw=wsfef+ewsff!fsf@ывуаыв#$%ыа^ыа&ыааы*234(ыа)"));
    }

    private String leaveOnlyLettersAndWhiteSpaces(String input) {
        return input.replaceAll("[^A-zА-я\\s]|\\^|`|\\\\", "");
    }
}
