import java.util.Scanner;

public class tic_toc_toe_3_3 {
	private static Player player1;
	private static Player player2;
	Board board;
	public static void main(String[] args) {
		tic_toc_toe_3_3 game = new tic_toc_toe_3_3();
		game.startgame();
	}	
	public void startgame() {

		Scanner s = new Scanner(System.in);	
		player1 = take_input(1);
		player2 = take_input(2);
		while(player1.get_player_symbol() == player2.get_player_symbol()) {
			System.out.println("symbol already taken!! please enter different symbol");
			player2.set_player_symbol(s.next().charAt(0));
		}
		board = new Board(player1.get_player_symbol(),player2.get_player_symbol());
		int status = Board.INVALID;
		boolean turn = true;
		board.print_array();
		while(status == Board.INVALID || status == Board.INCOMPLETE) {
			if(turn) {
				System.out.println("1 player turn");
				System.out.println("enter coordinates");
				int i = s.nextInt();
				int j = s.nextInt();
				status = board.move(i,j,player1.get_player_symbol());
				if(status == Board.INVALID) {
					System.out.println("please enter valid coordinates");
					continue;
				}
			}else {
				System.out.println("2 player turn");
				System.out.println("enter coordinates");
				int i = s.nextInt();
				int j = s.nextInt();
				status = board.move(i,j,player2.get_player_symbol());
				if(status == Board.INVALID) {
					System.out.println("please enter valid coordinates");
					continue;
				}				
			}
			turn = !turn;
			board.print_array();
		}
		if(status == 1) {
			System.out.println("1 win");
		}else if(status == 2) {
			System.out.println("2 win");
		}else {
			System.out.println("draw");
		}
	}
	private Player take_input( int x) {
		Scanner s = new Scanner(System.in);	
		System.out.println("enter player "+ x +" name");		
		String name1 = s.nextLine();
		System.out.println("enter player "+ x +" symbol");
		char symbol1 = s.next().charAt(0);
		return new Player(name1,symbol1);
	}
}

	

	
	class Player {
		private String player_name;
		private char symbol;

		public Player(String player_name,char player_symbol) {
			set_player_name(player_name);
			set_player_symbol(player_symbol);
		}
		public void set_player_name(String name) {
			if(!name.isEmpty()) {
				this.player_name = name;
			}
		}
		public String get_player_name() {
			return player_name;
		}
		public void set_player_symbol(char symbol) {
			if(symbol != '\0') {
				this.symbol = symbol;
			}
		}
		public char get_player_symbol() {
			return symbol;
		}
	}
	class Board {

		char[][] board = new char[4][4];
		int count=0;
		char p1symbol;
		char p2symbol;
		char empty = ' ';
		public static int INVALID =5;
		public static int PLAYER1_WINS = 1 ;
		public static int PLAYER2_WINS=2 ;
		public static int DRAW = 3 ;
		public static int INCOMPLETE=4 ;
		public Board(char p1symbol,char p2symbol) {
			for(int i = 0;i<4;i++) {
				for(int j = 0;j<4;j++) {
					if(i == 0) {
						board[i][j] = (char) (48+j);
					}
					else if(j == 0) {
						board[i][j] = (char) (48+i);
					}
					else	
					board[i][j] = empty;
				}
			}
			this.p1symbol = p1symbol;
			this.p2symbol = p2symbol;
		}
		public void print_array() {
			for(int i = 0;i<board.length;i++) {
				for(int j = 0;j<board.length;j++) {
					System.out.print("|" + board[i][j] + "|");
				}
				System.out.println();
			}
		}
		public int move(int i, int j, char symbol) {
			if(i>3 || j>3 || i<1 || j<1 || board[i][j] != empty) {
				return INVALID;
			}
			board[i][j] = symbol;
			count++;
			if(board[i][1] == board[i][2] && board[i][2] == board[i][3]) {
				return symbol == p1symbol ? PLAYER1_WINS : PLAYER2_WINS;
			}
			if(board[1][j] == board[2][j] && board[1][j] == board[3][j]) {
				return symbol == p1symbol ? PLAYER1_WINS : PLAYER2_WINS;
			}
			if(board[2][2] != empty && board[1][1] == board[2][2] && board[3][3] == board[2][2]) {
				return symbol == p1symbol ? PLAYER1_WINS : PLAYER2_WINS;
			}
			if(board[2][2] != empty && board[2][2] == board[1][3] && board[3][1] == board[2][2]) {
				return symbol == p1symbol ? PLAYER1_WINS : PLAYER2_WINS;
			}
			if(count == 9) {
				return DRAW;
			}
			return INCOMPLETE;
		}
	}
