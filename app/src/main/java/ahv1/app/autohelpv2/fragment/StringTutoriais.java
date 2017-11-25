package ahv1.app.autohelpv2.fragment;

/**
 * Created by Lucas Estacio on 23/11/2017.
 */

public class StringTutoriais {
    static String listdesc[][][][];

    public StringTutoriais() {    }

    public String[][][][] Stringtuto(){
             listdesc = new String[][][][]{
                     { //Problemas
                             {  // Elétrica
                                     {"Elétrica", "Faróis e Lâmpadas"},
                                     {"     Faróis queimados ", ""},
                                     {"     Faróis desregulados", ""}
                             },
                             {
                                     {"Elétrica", "Luzes do painel"},
                                     {"     Luz de óleo", ""},
                                     {"     Luz de freio ", ""},
                                     {"     Luz de ABS ", ""},
                                     {"     Luz de seta ", ""},
                                     {"     Luz de farol", ""},
                                     {"     Luz da injeção eletrônica acesa", ""}

                             },
                             {  // Elétrica
                                     {"Elétrica", "Bateria"},
                                     {"     Veículo não dá partida ou parte com dificuldade ", ""},
                                     {"     Veículo não da partida (embora luzes no painel acendam)", ""},
                                     {"     Veículo de repente apagou", ""}

                             }
                     },
                     {
                             {
                                     {"Rodas e pneus", "Rodas tortas"},
                                     {"     Rodas tortas", ""}
                             },
                             {
                                     {"Rodas e pneus", "Barulhos nas rodas"},
                                     {"     Barulhos nas rodas", ""}
                             },
                     },
                     {
                             {
                                     {"Suspensão ", "Vibrações no volante"},
                                     {"	Vibrações no volante", ""}
                             },
                             {
                                     {"Suspensão ", "Barulhos"},
                                     {"		Barulhos", ""}
                             }
                     },
                     {
                                     {
                                             {"Motor e câmbio ", "Problema no Motor"},
                                             {"		Motor 'Engasgando'", ""},
                                             {"		Falha no motor", ""}
                                     },
                                     {
                                             {"Motor e câmbio ", "Superaquecimento "},
                                             {"		Superaquecimento ", ""}
                                     },
                                     {
                                             {"Motor e câmbio ", "Vazamento de água  "},
                                             {"		Mangueiras", ""}
                                     },
                                     {
                                             {"Motor e câmbio ", "Fumaça  "},
                                             {"		Excesso de Fumaça", ""}
                                     },
                                     {
                                             {"Motor e câmbio ", "Vazamento"},
                                             {"		Vazamento de óleo ", ""}
                                     },
                                     {
                                             {"Motor e câmbio ", "Marcha "},
                                             {"		Marcha não entra", ""}
                                     }
                             },
                             {
                                     {
                                             {"Ar Condicionado ", "Sujeira "},
                                             {"    Sujeira no ar condicionado", ""}
                                     }

                             },
                             {
                                     {
                                             {"Freios ", "Problemas no Freio "},
                                             {"    Ruídos", ""},
                                             {"    Trepidação ao frear", ""},
                                             {"    Nível do óleo de freio abaixou", ""},
                                             {"    Desconforto nos pedais", ""}

                                     }
                             },
                             {
                                     {
                                             {" Problemas sonoros", "Barulhos em Geral "},
                                             {"    Som contínuo, como um pequeno aspirador de pó ligado", ""},
                                             {"    Pneus cantando ao fazer curvas em baixa velocidade", ""},
                                             {"    Rangidos e estalos ao passar por irregularidades, lombadas ou valetas", ""},
                                             {"    Ruídos ao virar o volante ", ""}

                                     }
                             },
                             {
                                     {
                                             {"Direção", "Problema no Volante"},
                                             {"    Volante duro e manobras mais difíceis", ""}
                                     }
                             },
                             {
                                     {
                                             {"Estrutura Geral", "Carro Torto"},
                                             {"   Carro puxando para um dos lados", ""}
                                     }
                             },
                     {
                             {
                                     {"Escapamento", "Aquecimento"},
                                     {"   Aquecimento do assoalho", ""}
                             }
                     },
                     {
                             {
                                     {"Amortecedores", "Estabilidade"},
                                     {"    Falta de Estabilidade", ""},
                                     {"    Balanços excessivos", ""}
                             }
                     },
                     {
                        {
                                {"Embreagem", "Embreagem"},
                                {"   Embreagem deslizando", ""},
                                {"   Embreagem com ruído", ""}

                        }
                     }
             };
        return listdesc;
    }

    public String[][][][] StringtutoResp(){
        listdesc = new String[][][][]{
                { //Problemas
                        {  // Elétrica
                                {"Faróis queimados ","Faróis desregulados"},
                                {"Luz de óleo", "Luz de freio ", "Luz de ABS ", "Luz de seta ", "Luz de farol", "Luz da injeção eletrônica acesa"},
                                {"Veículo não dá partida ou parte com dificuldade ", "Veículo não da partida (embora luzes no painel acendam)", "Veículo de repente apagou"}
                        }
                },
                {
                        {
                                {"Rodas tortas"},
                                {"Barulhos nas rodas"}
                        }
                },
                {
                        {
                                {"Vibrações no volante"},
                                {"Barulhos"}
                        }
                },
                {
                        {
                                {"Motor 'Engasgando'", "Falha no motor"},
                                {"Superaquecimento "},
                                {"Mangueiras"},
                                {"Excesso de Fumaça"},
                                {"Vazamento de óleo"},
                                {"Marcha não entra"},
                        }
                },
                {
                        {
                                {"Sujeira no ar condicionado"}
                        }

                },
                {
                        {
                                {"Ruídos", "Trepidação ao frear", "Nível do óleo de freio abaixou", "Desconforto nos pedais"}

                        }
                },
                {
                        {
                                {"Som contínuo, como um pequeno aspirador de pó ligado", "Pneus cantando ao fazer curvas em baixa velocidade",
                                        "Rangidos e estalos ao passar por irregularidades, lombadas ou valetas",
                                        "Ruídos ao virar o volante "}

                        }
                },
                {
                        {
                                {"Volante duro e manobras mais difíceis"}
                        }
                },
                {
                        {
                                {"Carro puxando para um dos lados"}
                        }
                },
                {
                        {
                                {"Aquecimento do assoalho"}
                        }
                },
                {
                        {
                                {"Falta de Estabilidade", "Balanços excessivos"}
                        }
                },
                {
                        {
                                {"Embreagem deslizando", "Embreagem com ruído"}

                        }
                }
        };
        return listdesc;
    }

    public String[][][][] StringtutorialResp(){
        String[][][][] listdescResp = new String[][][][]{
                { //Problemas
                        {  // Elétrica
                                {"Primeiramente, desconecte o terminal e retire o guarda-pó. Feito isso, " +
                                        "será necessário retirar uma trava que fixa a lâmpada do farol e," +
                                        " aí sim, puxar a lâmpada queimada para substituí-la pela nova. " +
                                        "Lembre-se de limpar os conectores (terminal) para evitar oxidações e " +
                                        "prejudicar o funcionamento da nova lâmpada. Coloque a nova lâmpada, " +
                                        "acompanhando as guias e encaixando-a até ouvir o “clic”. " +
                                        "Isso significa que ela já está pronta. Retorne o guarda-pó e o conector para finalizar o processo. O serviço, em média, não passa dos R$25.\n" +
                                        "Caso ainda esteja com dificuldades para entender esse procedimento, vá a um lugar especializado para realizar essa regulagem\t",

                                        "Pare o carro em um local plano e faça uma marca na parede com as medidas" +
                                                " dos centros dos faróis, esquerdo e direito, com a distância exata até o solo. Após isso recue o veículo sem girar a direção a uma distância de cinco metros. Uma outra marca deve ser feita, descendo um centímetro da marca original para cada metro recuado. Em duas folhas de papel, faça uma linha horizontal do centro para a esquerda e inclinada 15 graus para cima do centro para a direita. Cole o papel de maneira que o vértice da linha desenhada fique sobre a segunda marca deslocada para baixo. Estas são as referências para se regular o farol. Acenda o facho baixo e localize os parafusos de regulagem. Em cada farol existem sempre dois parafusos: um para a regulagem vertical e outro para a regulagem horizontal. " +
                                                "Gire-os até que as linhas de projeção coincidam com as marcas."},

                                {"Nessa situação a luz pode estar acesa devido a ausência de óleo, " +
                                        "dessa forma é necessário apenas completar. Porém, se houver óleo e a luz permanecer acesa é necessário tomar medidas rápidas e levar a uma mecânica, pois podem haver vários problemas, como por exemplo, vazamento de  óleo, defeitos na bomba, " +
                                        "defeito no filtro ou até mesmo pode ser que o motor esteja fundindo",

                                        "A solução para esse problema é simples. A peça defeituosa nesse caso" +
                                                " é o interruptor de luz de freio que fica no pedal, por ela passa muito energia e é uma peça que sofre constante pressão de nosso pé toda vez que freamos, devido a isso ela estraga com facilidade. Para consertar, basta trocar o interruptor que aciona essa luz," +
                                                " mas dependendo do modelo será necessário ir até uma oficina\n",

                                        "A luz de ABS acesa está indicando ao condutor uma falha ou defeito, " +
                                                "sejam nos sensores, atuadores, alimentação ou mesmo um dano em seu próprio software interno.  Então nem sonhe em apagar uma lâmpada, e corra para oficina para descobrir o que esta acontecendo. Esse problema não implica necessariamente que os freios não irão funcionar, " +
                                                "mas não é recomendado andar com as mesmas acesas.\n",

                                        "Ligue o alerta e veja se as lâmpadas de setas do painel piscam." +
                                                " Se piscarem, então a lâmpadas não estão queimadas, o que " +
                                                "indica ser um problema mais complexo para descobrir. " +
                                                "Mas se elas não piscarem, então muito provavelmente estarão " +
                                                "queimadas, ou com mau contato em seus soquetes. Se não piscar " +
                                                "depois destes testes, o painel deve ser retirado e um outro " +
                                                "teste será feito por ali. Um eletricista limpará os contatos das lâmpadas para em seguida, se for necessário, " +
                                                "prosseguir com um exame mais detalhado da situação. ",

                                        "Luz de farol",

                                        "O acendimento da luz de injeção eletrônica indica problemas em " +
                                                "algumas partes do veículo: bicos injetores, falhas em sensores " +
                                                "ou a indicação de que o combustível é incompatível com o motor — exemplo de adulteração. Assim que descobrir e resolver o problema, o normal é que essa luz apague. Caso ela continue acesa, procure pelos serviços da rede " +
                                                "autorizada de seu veículo e peça uma leitura com escaneamento.\n"},

                                {"A bateria pode estar descarregada devido a pouco uso\n" +
                                        "Desconecte o cabo negativo da Bateria quando o veículo ficar sem uso por longos períodos.\n" +
                                        "Mau contato devido a aperto insuficiente dos cabos, mau contato por oxidação entre os cabos\n" +
                                        "Aperte os parafusos de fixação.Se necessário, troque conectores ou cabos.Em caso de mau contato," +
                                        " remova os contatos e lixe os pontos de conexão.\n",

                                        "Fusível de Alimentação queimado.\n" +
                                                "Substitua o fusível\n" +
                                                "Motor de partida com defeito\n" +
                                                "Substitua o motor de partida\n" +
                                                "Veículo de repente apagou\n",

                                        "Substitua a Bateria"}
                        }
                },
                {
                        {
                                {"Você pode passá-las num torno e o serviço não ficará por mais de R$ 50" +
                                        " por roda. Depois de passá-las no torno, não se esqueça de " +
                                        "fazer o alinhamento e o balanceamento das 4 rodas. " +
                                        "O trabalho deve ser feito por um profissional especializado," +
                                        " principalmente porque envolve segurança e dirigibilidade do " +
                                        "automóvel. O alinhamento, como o próprio nome diz, serve para ajudar os ângulos das rodas, mantendo-as paralelas entre si. Já o balanceamento " +
                                        "serve para resolver problemas como vibrações no volante e trepidações."},

                                {"O barulho geralmente é causado pela parte metálica da pastilha estar " +
                                        "encostando na borda do disco de freio. De acordo com o mecânicos o barulho " +
                                        "metálico é um sintoma comum de que as pastilhas precisam ser trocadas."}
                        }
                },
                {
                        {
                                {"Se a vibração do volante  acontece quando se conduz o carro sem frenagem, quase sempre é um problema de balanceamento. A solução pode ser tão simples quanto reajustar o peso do balanceamento, " +
                                        "ou um custo um pouco mais alto de alinhamento e balanceamento completos."},

                                {"Os barulhos na suspensão podem ser provenientes de vários itens, " +
                                        "como por exemplo nas buchas das barras estabilizadoras, nas  bieletas das barras estabilizadoras, no rompimento dos pivôs, ou até mesmo nos amortecedores. Cada caso deve ser avaliado cuidadosamente para que" +
                                        " não se gaste dinheiro atoa e troque peças que não sejam necessárias.\n"}
                        }
                },
                {
                        {
                                {"Esse é um indício bastante comum de que algo está errado com o motor, e a sua causa também pode ser muito simples: combustível de má qualidade. Ao abastecer com um combustível adulterado, todo o sistema de combustão do carro é prejudicado. Por isso, caso você perceba sinais como dificuldade para dar a partida, motor “engasgando” ou falhando, marcha lenta irregular e perda de potência, " +
                                        "é bom procurar um novo posto de combustível para abastecer.",

                                        "Está geralmente ligado ao uso de combustível de má qualidade ou problemas nas velas e cabos de velas. Procure uma" +
                                                " oficina mecânica de confiança para que seja feito o diagnóstico correto."},

                                {"Uma vedação do cabeçote impede que o líquido de arrefecimento vaze. \n" +
                                        "Verifique a tampa do reservatório"},

                                {"Com o motor ligado, verifique se há bolhas se formando em algum ponto das mangueiras, tomando o cuidado de não encostar na ventoinha ou em partes quentes do motor. Se tiver a habilidade de retirá-las, passe sabão na parte externa e jogue água com pressão por dentro da mangueira. " +
                                        "Bolhas indicarão vazamento"},

                                {"Quando o motor do carro começa a esquentar, o motorista deve parar o carro imediatamente e acionar o guincho para levá-lo a uma oficina de confiança ou chamar um profissional especializado para ajudar. " +
                                        "Principalmente porque resolver por conta própria pode causar danos maiores."},

                                {"O motor é um sistema integrado por vários componentes Os fabricantes revelam que a origem do vazamento geralmente não começa no retentor. Portanto, se for necessário substituir o retentor, é sinal de que será preciso fazer uma revisão completa para descobrir a causa. Mas uma das principais causas do vazamento é provocada pelo próprio motorista: o excesso de óleo. Outro problema comum é vazamento pelo tampão do cárter (o reservatório do óleo). Muitas vezes o tampão não fica bem apertado após as trocas ou a junta está gasta. É importante lembrar que apenas " +
                                        "um mecânico pode identificar quais são as causas do vazamento de óleo."},

                                {"Esse é um problema comum em transmissões manuais. Normalmente, o problema é o nível baixo do fluido de embreagem, ou a viscosidade do líquido. No entanto, é possível que esteja acontecendo algo mais grave, " +
                                        "como danos na embreagem ou em outros componentes importantes do conjunto."},
                        }
                },
                {
                        {
                                {"Quando o ar-condicionado está sujo, ele pode criar um acúmulo de bactérias e ácaros prejudiciais à saúde respiratória. O ar-condicionado deve ser higienizado e ter o filtro trocado a cada seis meses, porém, em cidades com o ar muito poluído ou estradas de terra, esse tempo deve ser menor.O primeiro componente e o mais fácil de ser trocado é o filtro de ar ou filtro de pólen.  A troca do filtro mais a higienização custa entre R$ 30 e 60 R$ na maioria dos casos. Porém, alguns carros têm sistema mais complexo e " +
                                        "exigem mais tempo de mão de obra, assim, os preços podem chegar a R$ 100.\n"}
                        }

                },
                {
                        {
                                {"Quando houver ruído de freio alto, provocado pelo contato do sensor de desgaste mecânico contra a superfície do disco de freio, há grande chance de a pastilha estar gasta." +
                                        " Nesse caso é necessário levar a um especialista para ser trocada",

                                        "Este sintoma ocorre geralmente quando os discos estão empenados. Eles empenam quando estão muito finos (desgastados) ou quando a matéria-prima de fabricação está em desacordo com sua especificação. O problema aparece muitas vezes quando o disco está quente e " +
                                                "recebe um respingo de água fria, seja de uma poça ou de um alagamento.",

                                        "Nos carros mais novos este é um sintoma de que as pastilhas estão desgastadas. Se o carro possui mais de 30 mil km, é importante " +
                                                "checar se não há vazamento nos cilindros de freio das rodas traseiras.",

                                        "O desconforto ao acionar o pedal do freio, conhecido por “pedal duro”, pode estar relacionado a diversos fatores, como o tipo ou qualidade do material de atrito empregado nas pastilhas de freio, ou vazamento do diafragma do servo-freio. Mas, se ao pisar no freio o condutor sentir que o curso do “pedal está longo” o carro demora a frear, pode haver vazamento do fluido de freio. Outros fatores, como ar no circuito hidráulico, folga excessiva entre o pedal e a haste e lonas desreguladas também podem causar esse problema. Substituir as peças com defeito do sistema hidráulico, regular as lonas conforme indicado pelo fabricante do veículo, corrigir as folgas e fazer a sangria do " +
                                                "sistema de freio são algumas ações que podem corrigir este defeito"}

                        }
                },
                {
                        {
                                {"Se esse som acontecer quando o veículo estiver em movimento, pode ser necessário trocar os rolamentos das rodas. Se o problema surgir quando o ar-condicionado estiver ligado, pode ser problema de compressor. Já se o ruído contínuo aparecer ao esterçar o volante," +
                                        " a causa do problema pode ser a bomba da direção hidráulica.",

                                        "Se esse sintoma estiver associado à dificuldade em manter o carro em linha reta, o carro provavelmente precisa de alinhamento de rodas. É preciso estar atento a esse problema pois ele provoca aumento no consumo de combustível, " +
                                                "além do desgaste excessivo e irregular dos pneus.",

                                        "Esse som indica algum problema na suspensão e pode ser percebido nos batentes e coxins dos amortecedores, que são peças de borracha que absorvem o impacto do movimento vertical da roda. O carro com problemas " +
                                                "na suspensão força os amortecedores e reduz a vida útil desse componente.",

                                        "Se o ruído for identificado ao virar o volante para qualquer um dos lados, o problema pode estar na bomba da direção hidráulica. Caso ocorra ruído somente quando você esterça o volante para um dos lados, o problema pode ser encontrado na caixa da direção hidráulica. Outro problema relacionado com ruídos ao virar o volante está na baixa quantidade ou falta de óleo no motor. Isso pode ter ocorrido por um vazamento que, caso não seja reparado a tempo, pode prejudicar " +
                                                "a bomba de óleo e a caixa da direção hidráulica.\n"}

                        }
                },
                {
                        {
                                {"Quando isso acontece provavelmente o problema está na caixa de direção. Dificuldades para fazer manobras não podem passar despercebidas, por isso, atente-se.É importante que o carro seja levado a um mecânico de confiança para analisar o problema, pois é uma peça que está envolvida diretamente com a segurança do carro. Autorizadas e concessionárias " +
                                        "são os lugares mais indicados para reparar esse tipo de problema.\n"}
                        }
                },
                {
                        {
                                {"Este é um sintoma comum quando o carro não está alinhado. É preciso fazer também, a calibragem dos pneus, verificar desgastes nos pneus dianteiros, " +
                                        "folgas na suspensão e possíveis defeitos em peças do sistema de direção."}
                        }
                },
                {
                        {
                                {"O problema é decorrente da proximidade errada do sistema de escapamento ao chão do carro. Isso pode acontecer em função de alguma tortura no sistema. Também pode ser por causa do escapamento trincado. A solução é trocar a peça danificada. " +
                                        "Também é importante o cuidado com o alinhamento da nova peça."}
                        }
                },
                {
                        {
                                {"Amortecedores desgastados reduzem o nível de aderência do veículo ao solo, provocando falta de estabilidade e controle de direção. Essa condição é ainda mais grave em pisos molhados. Nestes casos, a ineficiência do componente pode causar, inclusive, aquaplanagens, aumentando consideravelmente o risco de acidentes. A vida útil do amortecedor é proporcional às condições de uso do veículo. A fabricante recomenda revisar preventivamente as condições dos amortecedores a cada 10.000 quilômetros, " +
                                        "ou conforme a orientação da fabricante do veículo.",

                                        "Amortecedores gastos provocam movimentação excessiva da carroceria como agachamentos e empinamentos. “Esses movimentos ocorrem principalmente em lombadas, valetas, buracos e desníveis do pavimento. É importante que o condutor fique atento ao comportamento do veículo nessas situações, que exigem mais da suspensão, para evitar danos maiores futuramente. A vida útil do amortecedor é proporcional às condições de uso do veículo. A fabricante recomenda revisar preventivamente as condições dos amortecedores a cada 10.000 quilômetros, " +
                                                "ou conforme a orientação da fabricante do veículo."}
                        }
                },
                {
                        {
                                {"Um dos problemas mais comuns é quando a embreagem fica deslizando ou patinando. Isso acontece por diversos motivos, em geral quando o disco da embreagem não consegue ser freado pelo platô, devido a algum dano ou mesmo excesso de resíduos, como graxa ou óleo. Trata-se de um problema mais sério, podendo comprometer o kit todo. Uma forma de identificá-lo é quando você solta o pedal e a embreagem continua acionada. " +
                                        "Nesse caso, o melhor a se fazer é substituir o kit completo.",

                                        "Geralmente, esse chiado pode ser causado pelo desgaste no rolamento, que não funciona mais como deveria. Leve o veículo à sua oficina imediatamente quando isso acontecer. Caso contrário, " +
                                                "a caixa do câmbio pode ser danificada, provocando prejuízos bem maiores.\n"}

                        }
                }
        };
        return listdescResp;
    }
}
