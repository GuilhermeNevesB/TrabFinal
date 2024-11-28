
-- Tabela Aluno
CREATE TABLE Aluno (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       matricula VARCHAR(20) NOT NULL UNIQUE,
                       data_nascimento DATE NOT NULL
);

-- Tabela Professor
CREATE TABLE Professor (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(100) NOT NULL,
                           email VARCHAR(100) NOT NULL UNIQUE,
                           telefone VARCHAR(20)
);

-- Tabela Curso
CREATE TABLE Curso (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       descricao TEXT
);

-- Tabela Disciplina
CREATE TABLE Disciplina (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            nome VARCHAR(100) NOT NULL,
                            carga_horaria INT NOT NULL,
                            curso_id INT NOT NULL,
                            FOREIGN KEY (curso_id) REFERENCES Curso(id)
);

-- Tabela Turma
CREATE TABLE Turma (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       professor_id INT NOT NULL,
                       disciplina_id INT NOT NULL,
                       FOREIGN KEY (professor_id) REFERENCES Professor(id),
                       FOREIGN KEY (disciplina_id) REFERENCES Disciplina(id)
);

-- Tabela Aluno_Turma (Relacionamento entre Aluno e Turma)
CREATE TABLE Aluno_Turma (
                             aluno_id INT NOT NULL,
                             turma_id INT NOT NULL,
                             PRIMARY KEY (aluno_id, turma_id),
                             FOREIGN KEY (aluno_id) REFERENCES Aluno(id),
                             FOREIGN KEY (turma_id) REFERENCES Turma(id)
);
