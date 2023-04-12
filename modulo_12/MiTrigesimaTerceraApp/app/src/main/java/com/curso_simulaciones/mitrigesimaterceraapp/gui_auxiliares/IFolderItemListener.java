package com.curso_simulaciones.mitrigesimaterceraapp.gui_auxiliares;

import java.io.File;

public interface IFolderItemListener {
    void OnCannotFileRead(File file);
    void OnFileClicked(File file);

}
