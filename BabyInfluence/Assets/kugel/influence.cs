using UnityEngine;
using System.Collections;

public class influence : MonoBehaviour {

    public GameObject ziel;
    public float speed;
    Vector3 moveDir;
    Vector3 randomPos;
    Vector3 goalDir;
    Vector3 move;
    int x;
    int y;
    

	// Use this for initialization
	void Start () {
	
        x = Random.Range(-100, 100);
        y = Random.Range(-100, 100);
	}
	
	// Update is called once per frame
	void Update () {
        randomPos = new Vector3(transform.position.x + x, transform.position.y + y, 0);

        goalDir = Vector3.Lerp(randomPos, ziel.transform.position, Time.deltaTime*2);
        moveDir = Vector3.Lerp(transform.position, goalDir, Time.deltaTime);




        transform.position = moveDir;
        Vector3 prof = transform.position - ziel.transform.position;
        if (prof.magnitude < 2) { Destroy(gameObject); }
       
	}
}
