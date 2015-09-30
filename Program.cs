using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RPS_game
{
    class Program
    {
        public static void tamashissaxeli()   //metodi dasasataureblad
        {
            Console.WriteLine("=====================================");
            Console.WriteLine("ROCK, PAPER, SCISSORS game");
            Console.WriteLine("=====================================");
            Console.WriteLine();
        }
        public static void shekitxva()     //metodi tamashis gagrdzeleba an shewyvetistvis(ar gamomivida)
        {
            string pasuxi;
            Console.WriteLine("Do you want to continue ? (Y or N) ");
            pasuxi = Console.ReadLine();
            switch (pasuxi)
            {
                case "y":
                case "Y":
                    break;
                case "n":
                case "N":
                    Console.WriteLine("GAME OVER");
                    Console.WriteLine("please,Close the window");
                    break;
            }
        }
        static void Main(string[] args)
        {
            tamashissaxeli();         //dasataureba
        aq:
            Console.WriteLine("ROCK, PAPER or SCISSORS(R, P, S)");
            string archevani;  //user-is archevani
            string comparchevani;
            const string rock = "ROCK", paper = "PAPER", scissors = "SCISSORS";
        here:
            string nivti = Console.ReadLine();
            switch (nivti)
            {
                case "r":
                case "R":
                    archevani = rock;
                    Console.WriteLine("you entered ROCK !");
                    break;
                case "p":
                case "P":
                    archevani = paper;
                    Console.WriteLine("you entered PAPER !");
                    break;
                case "s":
                case "S":
                    archevani = scissors;
                    Console.WriteLine("you entered SCISSORS !");
                    break;
                default:
                    Console.WriteLine("please,enter R, P or S and press ENTER");  //shecdomit mititetebis shemtxvevashi xelaxla rom mostxovos
                    goto here;
            }
            switch (archevani)         /*am switchshi vitvaliswineb yvela shemtxvevas rasac user airchevs da rogor 
                                            * unda moiqces programa satitaod am archevanis dros*/
            {
                case rock:
                    comparchevani = scissors;   /*aq ver vxvdebi rogor davwero kompiuterma xan erti rom airchios xan meore,
                                                     * ese marto pirveli rac weria imas irchevs,amitom sul AGEB :D*/
                    comparchevani = paper;
                    if (comparchevani == scissors)
                    {
                        Console.WriteLine("computer chose SCISSORS, you WIN !");
                    }
                    else
                    {
                        Console.WriteLine("computer chose paper, you LOSE :(");
                    }
                    break;
                case paper:
                    comparchevani = rock;
                    comparchevani = scissors;
                    if (comparchevani == rock)
                    {
                        Console.WriteLine("computer chose ROCK, you WIN !");
                    }
                    else
                    {
                        Console.WriteLine("computer chose SCISSORS,you LOSE :(");
                    }
                    break;
                case scissors:
                    comparchevani = paper;
                    comparchevani = rock;
                    if (comparchevani == paper)
                    {
                        Console.WriteLine("computer chose PAPER,you WIN !");
                    }
                    else
                    {
                        Console.WriteLine("computer chose ROCK,you LOSE :(");
                    }
                    break;
            }
            shekitxva();
            goto aq;
        }
    }
}
