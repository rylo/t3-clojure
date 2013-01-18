(ns tictactoe.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe.board :refer :all]))

(describe "Board"
	(it "contain 5 spaces"
		(let [board [" ", " ", " ", " ", " "]]
			(should (= (board-size board) 5))))
			
	(it "contains 9 elements"
		(let [board (take 9 (repeat " "))]
			(should (= (board-size board) 9))))
		
	(it "creates a board with 9 blank spaces"
		(let [board (make-board 9 " ")]
			(should= 9 (board-size board))
			(dotimes [n 9] (should= " " (get-marker board n)))))
	
	(it "returns the mark at a given space"
		(let [board (make-board 9 "0")]
			(should= (get-marker board 8) "0")))
			
	(context "set-marker"
		(it "returns a board with a changed marker at the index given"
			(let [board (make-board 9 "0") board2 (set-marker board "x" 5)]
				(should= ["0" "0" "0" "0" "0" "0" "0" "0" "x"] (set-marker board "x" 8))
				(should= (set-marker board "x" 3) ["0" "0" "0" "x" "0" "0" "0" "0" "0"])
				(should= (set-marker board "x" 4) ["0" "0" "0" "0" "x" "0" "0" "0" "0"])
				(should= (set-marker board "x" 1) ["0" "x" "0" "0" "0" "0" "0" "0" "0"]))))
	
	(context "get-rows"
		(it "returns the board's 'horizontal' rows" 
			(let [board (make-board 9 "0") row-guide [[0 1 2] [3 4 5] [6 7 8]]]
				(should= (get-rows (set-marker board "x" 2) row-guide) [["0" "0" "x"] ["0" "0" "0"] ["0" "0" "0"]])
				(should= (get-rows (set-marker board "x" 4) row-guide) [["0" "0" "0"] ["0" "x" "0"] ["0" "0" "0"]])))
	
		(it "returns a single row from the board" 
			(let [board (make-board 9 "0") row-guide [[0 1 2]]]
				(should= (get-rows (set-marker board "x" 2) row-guide) [["0" "0" "x"]])))
	
		(it "returns the board's 'vertical' rows" 
			(let [board (make-board 9 "0") row-guide [[0 3 6] [1 4 7] [2 5 8]]]
				(should= (get-rows (set-marker board "x" 2) row-guide) [["0" "0" "0"] ["0" "0" "0"] ["x" "0" "0"]])
				(should= (get-rows (set-marker board "x" 3) row-guide) [["0" "x" "0"] ["0" "0" "0"] ["0" "0" "0"]]))))
	
	(it "returns the spaces at the locations in a vector"
		(let [board (set-marker (make-board 9 "0") "x" 1)]
			(should= ["0" "x" "0" "0" "0" "0" "0" "0" "0"] board)
			(should= ["0" "0" "0"] (get-markers board [0 3 6]))
			(should= ["0" "x" "0"] (get-markers (set-marker board "x" 3) [0 3 6]))))
	
	(it "returns the board's diagonal rows" 
		(let [board (make-board 9 "0") row-guide [[0 4 8] [2 4 6]]] 
			(should= [["0" "x" "0"] ["0" "x" "0"]] (get-rows (set-marker board "x" 4) row-guide))))

	(context "row-taken?"
		(it "returns false if the row doesn't contain 3 of the same string" 
			(let [board (make-board 9 nil) row [0 1 2]]
				(should= false (row-taken? board row))))
				
		(it "returns true if the row contains 3 of the same string" 
			(let [board (make-board 9 "x") row [0 1 2]]
				(should= true (row-taken? board row)))))

	(context "row-taken-by?"
		(it "returns false if the row doesn't contain 3 of the same string" 
			(let [board (make-board 9 nil) row (get-rows board [[1 2 3]])]
				(should= false (row-taken-by? "x" board row))))

		(it "returns true if the row contains 3 of the same string" 
			(let [board (make-board 9 "x") row (get-rows board [[1 2 3]])]
				(should= true (row-taken-by? "x" board row)))))
				
	(context "winning-row-present?"
		(it "returns true if a row is taken"
			(let [empty-board (make-board 9 nil) full-board (make-board 9 "x") full-row-vector (generate-rows)]
				(should= false (winning-row-present? empty-board))
				(should= true (winning-row-present? full-board))
				(should= true (winning-row-present? ["x" "x" "x" "o" "o" nil nil nil nil]))
				(should= true (winning-row-present? [nil "x" "x" "o" "o" "o" nil nil nil]))
				(should= false (winning-row-present? ["x" "o" "o" "o" "o" nil nil nil nil])))))
				
	(context "generate-rows"
		(it "returns a vector of row vectors"
			(let [board (make-board 9 nil) full-row-vector [[0 1 2][3 4 5][6 7 8][0 3 6][1 4 7][2 5 8][0 4 8][2 4 6]]]
				(should= full-row-vector (generate-rows)))))

	(context "printable-board"
		(it "returns a nicely formatted board!"
			(let [board (make-board 9 "x")]
				(should= "_______\n|x|x|x|\n|x|x|x|\n|x|x|x|\n_______" (printable-board board)))))

	(context "printable-row"
		(it "returns a nicely formatted row!"
			(let [row '(x x x)] (should= "|x|x|x|\n" (printable-row row)))))
			
	(context "full?"
		(it "should return true if the board has 9 moves on it"
			(let [board (make-board 9 "x")]
				(should= true (full? board))))
				
		(it "should return false if the board has less than 9 moves on it"
			(let [board (make-board 9 nil)]
				(should= false (full? board)))))
				
	(context "replace-nil"
		(it "should replace a nil value with a space"
			(should= " " (replace-nil nil))
			(should= 1 (replace-nil 1))
		)
	)

)

(run-specs)