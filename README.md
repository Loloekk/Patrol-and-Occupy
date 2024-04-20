# Patrol-and-Occupy

Projekt, który aktualnie jest udostępniony, jest jedynie przykładem, na kótrym testujemy działanie biblioteki.

\documentclass[polish,12pt,a4paper]{extarticle}
\usepackage[T1]{fontenc}

\usepackage[top=2.5cm,bottom=2cm,left=2cm,right=2cm]{geometry}
\usepackage{xcolor}

\usepackage{babel}
\usepackage{titling}
\usepackage{lastpage}
\usepackage{amsmath}
\usepackage{amssymb}
\usepackage{stmaryrd}
\usepackage{fancyhdr}
\usepackage[bookmarks=false]{hyperref}
\usepackage{algorithm2e}
\usepackage{mathtools}
\usepackage{overarrows}
\pagestyle{fancy}

\title{}
\author{}

\fancyhead[l]{Karol Bielaszka, Mateusz Gabzdyl, Jacek Markiewicz}
\fancyhead[c]{\textbf{\thetitle}}
\fancyhead[r]{\theauthor}
\fancyfoot[c]{\begin{NoHyper}\thepage/\pageref{LastPage}\end{NoHyper}}

\newcommand{\inident}{\hspace{1.25em}}

% Algo stuff
\newcommand\mycommfont[1]{\ttfamily\textcolor{gray}{#1}}
\SetCommentSty{mycommfont}
\newcommand\Field[2]{\textbf{Field} $#1$: \texttt{#2}\;}
\newcommand\Method[3]{
	\SetKwFunction{Fn#1#2}{#1.#2}
	\SetKwFunction{FnLocal#1#2}{#2}
	\MethodImpl{\textnormal{\texttt{#2}(#3)}}
}
\newcommand\Struct[1]{
	\SetKwFunction{St#1}{#1}
	\StructImpl{\textnormal{\texttt{#1}}}
}

\newcommand\initalgorithm{
	\SetAlgoLined
	\DontPrintSemicolon
	\SetKwProg{Fn}{function}{:}{end}
	\SetKwProg{StructImpl}{struct}{:}{end}
	\SetKwProg{MethodImpl}{method}{:}{end}
}

% Math Stuff
\newcommand\Nat{\mathbb{N}}
\newcommand\Primes{\mathbb{P}}
\newcommand\eqqm[0]{\stackrel{?}{=}}
\renewcommand\lor{\,\vee\,}
\renewcommand\land{\,\wedge\,}
\newcommand\lxor[0]{\,\veebar\,}
\newcommand\union[0]{\cup}
\newcommand\isect[0]{\cap}
\newcommand\Union[0]{\bigcup}
\newcommand\Isect[0]{\bigcap}
\newcommand\nil[0]{\emptyset}
\renewcommand\geq{\geqslant}
\renewcommand\leq{\leqslant}
\newcommand\eqs[1]{\stackrel{#1}{=}}
\newcommand\impliesqm[0]{\stackrel{?}{\implies}}
\newcommand\QED[0]{\hfill$\blacksquare$}	

\newcommand\set[1]{\left\{#1\right\}}
\newcommand\card[1]{\left|#1\right|}
\newcommand\cset[1]{\card{\set{#1}}}
\DeclarePairedDelimiter{\floor}{\lfloor}{\rfloor}
\DeclarePairedDelimiter{\ceil}{\lceil}{\rceil}

\newcommand{\stirC}[2]{\genfrac{[}{]}{0pt}{}{#1}{#2}}
\newcommand{\stirP}[2]{\genfrac{\{}{\}}{0pt}{}{#1}{#2}}

\NewOverArrowCommand{image}{}
\NewOverArrowCommand{coimage}{
	end=\leftarrow
}

\newcommand\injarr[0]{\hookrightarrow}
\newcommand\surarr[0]{\rightarrow\mathrel{\mspace{-15mu}}\rightarrow}
\newcommand\bijarr[0]{\hookrightarrow\mathrel{\mspace{-15mu}\rightarrow}}


\begin{document}
	\section{Tytuł projektu: Patrol and Occupy}
            
        \section{Skład}
        \begin{itemize}
        \item Karol Bielaszka (grupa 3) 
        \item Mateusz Gabzdyl (grupa 3) 
        \item Jacek Markiewicz (grupa 2)
        \end{itemize}

        \section{Opis projektu}
        Patrol and Occupy to gra przeznaczona dla 2, 3 lub 4 graczy. Plansza gry będzie się składać z miejsc spawnu, przeszkód stałych oraz ruchomych, dynamitów oraz baz. Gracz sterując czołgiem będzie miał za zadanie zająć jak najwięcej baz w określonym czasie. Zajęcie bazy polega na wjechaniu czołgiem na jej teren. Gracz może odbić bazę zajętą przez innego gracza. Czołgi mogą strzelać pociskami. Kiedy gracz zostanie trafiony przez pocisk przeciwnika, to jego pojazd się psuje, automatycznie zostaje teleportowany na miejsce spawnu i musi odczekać kilka sekund zanim znowu będzie mogł brać aktywny udział w grze. Jeżeli pocisk gracza trafi w dynamit, to dynamit ten wybucha psując wszystkie pojazdy, które znajdują się w pobliżu. Gracz może swoim pojazdem przepychać ruchome przeszkody. Wygrywa gracz, który po upływie czasu przeznaczonego na rundę, będzie miał zajęte najwięcej baz.\\

        \noindent W trybie dla dwóch osób sterowanie pojazdami będzie się odbywało domyślnie za pomocą przycisków A, W, D, SPACE dla pierwszgo gracza, oraz strzałek i prawego CTRL dla drugiego gracza.\\

        \noindent W pozostałych trybach na jednego gracza będzie przypadał jeden przycisk (które dokładnie przyciski, to jest jeszcze do ustalenia).\\\\\
        \textbf{Opcjonalne rozszerzenia}
        \begin{itemize}
            \item Ustawienia, umożliwiające zmianę długości trwania rundy oraz zmianę klawiszów do sterowania pojazdami;
            \item magazynek pocisków, pojazd może wystrzelić pocisk, tylko jeżeli magazynek nie jest pusty. Magazynek posiada ograniczoną ilość miejsc. Co sekundę każdy pojazd, którego magazynek nie jest pełny otrzymuje jeden pocisk;
            \item możliwość rozstawiania dynamitów, które wybuchają po jakimś czasie;
            \item dźwiek strzałów, wybuchów oraz zniszczeń pojazdów;
            \item dodanie botów, co umożliwiło by granie samemu.
        \end{itemize}
        \section{Biblioteki/Framework}
        \begin{itemize}
        \item libGDX
        \end{itemize}
        \section{\href{https://github.com/Loloekk/Patrol-and-Occupy}{Repository}}
        
 
\end{document}


