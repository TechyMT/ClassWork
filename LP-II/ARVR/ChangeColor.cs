using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ColorChanger : MonoBehaviour
{

    public Renderer sphereRenderer;
    public GameObject sphere;

    [SerializeField] private Color newcolor;
    [SerializeField] private Color[] colors;
    private int colorvalue;
    // Start is called before the first frame update
    void Start()
    {
        sphereRenderer = sphere.GetComponent<Renderer>();
    }

    public void ChangeCol()
    {
        newcolor = Random.ColorHSV();

        sphereRenderer.material.color = newcolor;
    }

    public Material newMat1;
    public Material newMat2;
    private bool mat1applied = true;

    public void ChangeMat()
    {
        if(mat1applied)
        {
            sphereRenderer.material = newMat2;
            mat1applied = false;
        }
        else
        {
            sphereRenderer.material = newMat1;
            mat1applied = true;
        }
    }
    
}