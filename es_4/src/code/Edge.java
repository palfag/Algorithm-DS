package code;

import java.lang.*;

public class Edge<T, W>  {
    private W weight;
    private T start;
    private T dest;


    public Edge(T start, T dest, W weight){
        this.start = start;
        this.dest = dest;
        this.weight = weight;
    }

    public W getWeight() {
        return weight;
    }

    public T getStart() {
        return start;
    }

    public T getDest() {
        return dest;
    }
    


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dest == null) ? 0 : dest.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge<?,?> other = (Edge<?,?>) obj;
        if (dest == null) {
            if (other.dest != null)
                return false;
        } else if (!this.dest.equals(other.dest))
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        return true;
    }
    
}
