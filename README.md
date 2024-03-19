**Projeto Java Spring + Angular - Hoje**

**Tecnologia**

- O sistema precisa cadastrar:
  - Uma nome, tem um tipo (Visitante, Condômino, Administrador), cpf, email, telefone. apelido, foto de e um ID único (UUID) pois ele pode ser compartilhado entre sistemas diferentes.
  - Os campos obrigatórios serão: nome, cpf, email e id unico.
  - Os campos CPF e EMAILdevem ser únicos e deve ser validado no servidor. Caso tente cadastrar um já existente, deve retornar mensagem de erro.
- O sistema precisa cadastrar unidades:
  - Uma tem a descrição, sala, andar e também um ID único (UUID) pois ele ser compartilhado entre sistemas diferentes.
  - Todos campos serão obrigatórios.
- Uma pessoa tem acesso a N unidades e uma unidade estar vinculada a N pessoas. Pense em como fazer esta relação.
- O aplicativo (frontend) deve criar unidades (CRUD).
  - Antes de começar o CRUD, vamos em como fazer uma tela simples para realizar essas operações.
- O aplicativo deve criar pessoas e nessa tela de pessoa, vincular N unidades.
- Faça comunicação entre back e front utilizando REST.
- Não se abstenha em deixar visualmente agradável, o importante é que tenha um formulário básico com botão e que realize a operação. Não irei avaliar absolutamente nada referente UX/UI.
- Tente fazer o que lista e seleciona uma unidade, e selecionar, ele vai retornar o ID da unidade, para vincular a pessoa.
- Pense em separar as responsabilidades, principalmente no backend.
- Se preocupe com qualidade, não foque em fazer rápido, mas foque em fazer de maneira inteligente. Esteja pronto para arriscar, errar.
- Certamente estará faltando alguns requisitos, e você vai me que a gente vai evoluindo, mas não passará muito desse escopo para não tomar tempo e energia, o objetivo aqui é exercitar algumas práticas.
