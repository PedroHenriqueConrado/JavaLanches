CREATE TABLE Notas (
                       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- Identificador único da nota
                       matricula_id INT NOT NULL,                  -- Chave estrangeira para Matriculas
                       disciplina_id INT NOT NULL,                 -- Chave estrangeira para Disciplinas
                       nota DECIMAL(5, 2) NOT NULL,                -- Nota com duas casas decimais
                       data_lancamento DATE NOT NULL,              -- Data de lançamento da nota
                       FOREIGN KEY (matricula_id) REFERENCES Matriculas(id) ON DELETE CASCADE ON UPDATE CASCADE,
                       FOREIGN KEY (disciplina_id) REFERENCES Disciplinas(id) ON DELETE CASCADE ON UPDATE CASCADE
);
