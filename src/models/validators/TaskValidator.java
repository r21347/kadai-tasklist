package models.validators;

import models.Task;

public class TaskValidator {

    // バリデーションを実行する
    public static String validate(Task t) {
        String error = validateContent(t.getContent());
        return error;
    }

    //内容の必須入力チェック
    private static String validateContent(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください。";
        }

        return null;
    }
}