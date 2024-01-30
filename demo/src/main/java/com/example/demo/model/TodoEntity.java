package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Todo")
public class TodoEntity {
	private String id; // �� ������Ʈ�� ���̵�
	private String userId; // �� ������Ʈ�� ������ ������ ���̵�
	private String title; // Todo Ÿ��Ʋ ��) ��ϱ�
	private boolean done; // true - todo�� �Ϸ��� ���
}