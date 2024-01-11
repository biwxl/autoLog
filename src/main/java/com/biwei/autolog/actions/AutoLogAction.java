package com.biwei.autolog.actions;

import com.biwei.autolog.Constans;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class AutoLogAction extends PsiElementBaseIntentionAction {
    @Override
    public void invoke(@NotNull Project project, Editor editor, @NotNull PsiElement psiElement) throws IncorrectOperationException {

    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, @NotNull PsiElement psiElement) {
        if (!(psiElement instanceof PsiFile)) {
            for (PsiElement run = psiElement.getParent(); run != null; run = run.getParent()) {
                if (instanceOf(run, PsiMethod.class) || instanceOf(run, PsiClass.class)) {
                    return true;
                }
                if (run instanceof PsiFile) {
                    break;
                }
            }
        }
        return false;
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return Constans.AUTO_LOG_THIS_CLASS;
    }

    public static boolean instanceOf(Object object, Class<?>... classes) {
        if (object != null && classes != null) {
            Class[] var2 = classes;
            int var3 = classes.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Class<?> c = var2[var4];
                if (c.isInstance(object)) {
                    return true;
                }
            }
        }

        return false;
    }

}
