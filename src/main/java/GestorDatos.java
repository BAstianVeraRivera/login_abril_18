import java.io.*;
class GestorDatos {
    private String archivo;

    public String getArchivo() {return archivo;}

    public void setArchivo(String archivo) {this.archivo = archivo;}

    public GestorDatos(String archivo) {
        this.archivo = archivo;
    }
    public void guardarUsuario(Usuario usuario) {
        try {
            FileWriter fw = new FileWriter(archivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(usuario.getNombre() + "," + usuario.getPassword());
            pw.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String buscarUsuario(String nombre) {
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(nombre)) {
                    br.close();
                    fr.close();
                    return datos[1];
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}