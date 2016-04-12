package com.eribeiro.volleysimples.models;

/**
 * Created by edwin on 29/03/16.
 */
public class CourseItemModel {
    private String nome_do_curso, descricao_do_curso;
    int resource_course_id;

    public CourseItemModel(String nome_do_curso, String descricao_do_curso, int resource_course_id) {
        this.nome_do_curso = nome_do_curso;
        this.descricao_do_curso = descricao_do_curso;
        this.resource_course_id = resource_course_id;
    }

    public String getNome_do_curso() {
        return nome_do_curso;
    }

    public void setNome_do_curso(String nome_do_curso) {
        this.nome_do_curso = nome_do_curso;
    }

    public String getDescricao_do_curso() {
        return descricao_do_curso;
    }

    public void setDescricao_do_curso(String descricao_do_curso) {
        this.descricao_do_curso = descricao_do_curso;
    }

    public int getResource_course_id() {
        return resource_course_id;
    }

    public void setResource_course_id(int resource_course_id) {
        this.resource_course_id = resource_course_id;
    }
}
