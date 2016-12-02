using UnityEngine;
using System.Collections;

public class goldenMenue : MonoBehaviour {

    AudioSource audio;
    public GameObject kugel;
    public int kistenArt;


    // Use this for initialization
    void Start()
    {
        audio = gameObject.GetComponent<AudioSource>();

    }

    public void clickDown()
    {
        Vector3 mousePosition = Input.mousePosition;
        mousePosition.z = 40;
        mousePosition = Camera.main.ScreenToWorldPoint(mousePosition);
        Debug.Log(mousePosition);
        GameObject kug = (GameObject)Instantiate(kugel, mousePosition, Quaternion.identity);
        kug.GetComponent<Rigidbody>().AddForce(-600, 0, 0, ForceMode.Impulse);


        //(gameObject.transform.position + new Vector3(0, 10, 0))
    }

    public void playsound()
    {

        audio.Play();
    }

  public  void functionality()
    {
        switch (kistenArt)
        {
            case 0: Application.Quit(); break;
            case 1: Application.LoadLevel("1"); break;
        }
    }
}
