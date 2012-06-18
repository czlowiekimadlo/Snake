package pl.edu.amu.wmi.lsm.snake;

public class Score {

	Integer _id;
	String _name;
	Integer _score;

	public Score() {

	}

	public Score(Integer id, String name, Integer score) {

		this._id = id;
		this._name = name;
		this._score = score;
	}

	public Score(String name, Integer score) {

		this._name = name;
		this._score = score;
	}
	
	public Score(Integer score) { //dodatkowy konstruktor tylko dla samego wyniku

		this._score = score;
	}

	public Integer getID() {

		return this._id;
	}

	public void setID(Integer id) {
		this._id = id;

	}

	public String getName() {
		return this._name;

	}

	public void setName(String name) {

		this._name = name;
	}

	public Integer getScore() {
		return this._score;

	}

	public void setScore(Integer score) {

		this._score = score;
	}
}
