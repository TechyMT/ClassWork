using UnityEngine;

public class transform : MonoBehaviour
{
    public float rotationSpeed = 50f;
    public float moveSpeed = 2f;

    void Update()
    {
        transform.Rotate(Vector3.up, rotationSpeed * Time.deltaTime);  //Rotate around y-axis

        transform.Translate(Vector3.forward * moveSpeed * Time.deltaTime);  //move forward
    }
}