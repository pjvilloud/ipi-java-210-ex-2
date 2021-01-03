import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;

public class TestUtils {
    public static final String OBJECT = "java.lang.Object";
    public static final String VOID = "java.lang.Void";
    public static final String HASHSET = "java.util.HashSet";
    public static final String STRING = "java.lang.String";
    public static final String DOUBLE = "java.lang.Double";
    public static final String LOCAL_DATE = "org.joda.time.LocalDate";
    public static final String INTEGER = "java.lang.Integer";
    public static final String LIST = "java.util.List";
    public static final String LONG = "java.lang.Long";
    public static final String PAGE = "org.springframework.data.domain.Page";
    public static final String PAGEABLE = "org.springframework.data.domain.Pageable";

    public static Object callDMethod(String classe, String methodName, Class... parameters) throws Exception {
        Method method = getClasse(classe).getDeclaredMethod(methodName, parameters);
        method.setAccessible(true);
        return method.invoke(null);
    }

    public static Object callMethod(Class classe, String methodName, Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = classe.getMethod(methodName);
        method.setAccessible(true);
        return method.invoke(null, parameters);
    }

    public static Object callMethod(Object o, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = o.getClass().getMethod(methodName);
        method.setAccessible(true);
        Object resultat = method.invoke(o);
        return resultat;
    }

    public static Class toPrimitiveType(Class type){
        if(type == Integer.class) {
            return int.class;
        }
        else if(type == Double.class) {
            return double.class;
        }
        else if(type == Character.class) {
            return char.class;
        }
        else if(type == Byte.class) {
            return byte.class;
        }
        else if(type == Boolean.class) {
            return boolean.class;
        }
        else if(type == Float.class) {
            return float.class;
        }
        else if(type == Long.class) {
            return long.class;
        }
        else {
            return short.class;
        }
    }

    public static Object callMethod(Object o, String methodName, Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] params = parameters.clone();
        Class[] classes = new Class[parameters.length];

        for(int i = 0; i < parameters.length; i++){
            classes[i] = params[i].getClass();
        }
        Method method = o.getClass().getMethod(methodName, classes);
        method.setAccessible(true);
        Object resultat = method.invoke(o, parameters);
        return resultat;
    }

    public static Object callDeclaredMethod(String o, String methodName, Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Object[] params = parameters.clone();
        Class[] classes = new Class[parameters.length];

        for(int i = 0; i < parameters.length; i++){
            classes[i] = params[i].getClass();
        }
        Method method = Class.forName(o).getDeclaredMethod(methodName, classes);
        method.setAccessible(true);
        Object resultat = method.invoke(o, parameters);
        return resultat;
    }

    public static Object callMethodPrimitiveParameters(String o, String methodName, Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Object[] params = parameters.clone();
        Class[] classes = new Class[parameters.length];

        for(int i = 0; i < parameters.length; i++){
            classes[i] = toPrimitiveType(params[i].getClass());
        }
        Method method = Class.forName(o).getDeclaredMethod(methodName, classes);
        method.setAccessible(true);
        Object resultat = method.invoke(o, parameters);
        return resultat;
    }

    public static Object callDeclaredMethodPrimitiveParameters(Object o, String methodName, Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object[] params = parameters.clone();
        Class[] classes = new Class[parameters.length];

        for(int i = 0; i < parameters.length; i++){
            classes[i] = toPrimitiveType(params[i].getClass());
        }
        Method method = o.getClass().getDeclaredMethod(methodName, classes);
        method.setAccessible(true);
        Object resultat = method.invoke(o, parameters);
        return resultat;
    }

    public static void checkStaticMethod(String classe, String nomMethode, String returnType, Class... paramTypes) throws Exception {
        checkStaticMethod(getClasse(classe), nomMethode, getClasse(returnType), paramTypes);
    }


    private static void checkStaticMethod(Class classe, String nomMethode, Class returnType, Class... paramTypes) {
        Method method = null;
        try {
            method = classe.getDeclaredMethod(nomMethode, paramTypes);
            Assertions.assertThat(method.getReturnType()).as("La méthode " + nomMethode + " doit retourner le bon type").isEqualTo(returnType);
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Aucune méthode nommée " + nomMethode + " n'a été trouvée ou les paramètres sont incorrects");
        }
//        Assertions.assertThat(Modifier.isPublic(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas publique").isTrue();
        Assertions.assertThat(Modifier.isStatic(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas statique").isTrue();

    }

    public static Method checkMethod(String classe, String nomMethode, String returnType, String... parameters) throws Exception{
        return checkMethod(getClasse(classe), nomMethode, getClasse(returnType), Stream.of(parameters).map(t -> {
            try {
                return getClasse(t);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }return null;
        }).collect(Collectors.toList()).toArray(new Class[0]));
    }

    public static Class<?> getClasse(String classe) throws ClassNotFoundException {
        if("void".equals(classe)){
            return void.class;
        } else if(classe.equals("short")){
            return short.class;
        } else if(classe.equals("short[]")){
            return short[].class;
        } else if(classe.equals("boolean")){
            return boolean.class;
        }
        return Class.forName(classe.contains(".") ? classe : classe);
    }

    @Deprecated
    public static Method checkMethod(Class classe, String nomMethode, Class returnType, Class... parameters) {
        Method method = null;
        try {
            method = classe.getDeclaredMethod(nomMethode, parameters);
            Assertions.assertThat(method.getReturnType()).as("La méthode " + nomMethode + " doit retourner le bon type").isEqualTo(returnType);
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Aucune méthode nommée " + nomMethode + " n'a été trouvée");
        }
        Assertions.assertThat(Modifier.isPublic(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas publique").isTrue();
        return method;
    }

    public static void checkFinalMethod(String classe, String nomMethode, String returnType, String... parameters) throws Exception {
        //noinspection deprecation
        checkFinalMethod(getClasse(classe), nomMethode, getClasse(returnType), Stream.of(parameters).map(t -> {
            try {
                return getClasse(t);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }return null;
        }).collect(Collectors.toList()).toArray(new Class[0]));
    }

    private static void checkFinalMethod(Class classe, String nomMethode, Class returnType, Class... parameters) {
        Method method = null;
        try {
            method = classe.getDeclaredMethod(nomMethode, parameters);
            Assertions.assertThat(method.getReturnType()).as("La méthode " + nomMethode + " doit retourner le bon type").isEqualTo(returnType);
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Aucune méthode nommée " + nomMethode + " n'a été trouvée");
        }
        Assertions.assertThat(Modifier.isFinal(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas final").isTrue();

    }

    public static void checkPrivateMethod(String classe, String nomMethode, String returnType, String... parameters) throws Exception {
        //noinspection deprecation
        checkPrivateMethod(getClasse(classe), nomMethode, getClasse(returnType), Stream.of(parameters).map(t -> {
            try {
                return getClasse(t);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }return null;
        }).collect(Collectors.toList()).toArray(new Class[0]));
    }

    private static void checkPrivateMethod(Class classe, String nomMethode, Class returnType, Class... parameters) {
        Method method = null;
        try {
            method = classe.getDeclaredMethod(nomMethode, parameters);
            method.setAccessible(true);
            Assertions.assertThat(method.getReturnType()).as("La méthode " + nomMethode + " doit retourner le bon type").isEqualTo(returnType);
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Aucune méthode nommée " + nomMethode + " n'a été trouvée");
        }
        Assertions.assertThat(Modifier.isPrivate(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas privée").isTrue();

    }

    public static void checkAbstractMethod(String classe, String nomMethode, String returnType, String... parameters) throws Exception {
        //noinspection deprecation
        checkAbstractMethod(getClasse(classe), nomMethode, getClasse(returnType), Stream.of(parameters).map(t -> {
            try {
                return getClasse(t);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }return null;
        }).collect(Collectors.toList()).toArray(new Class[0]));
    }

    private static void checkAbstractMethod(Class classe, String nomMethode, Class returnType, Class... parameters) {
        Method method = null;
        try {
            method = classe.getDeclaredMethod(nomMethode, parameters);
            Assertions.assertThat(method.getReturnType()).as("La méthode " + nomMethode + " doit retourner le bon type").isEqualTo(returnType);
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Aucune méthode nommée " + nomMethode + " n'a été trouvée");
        }
        Assertions.assertThat(Modifier.isPublic(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas publique").isTrue();
        Assertions.assertThat(Modifier.isAbstract(method.getModifiers())).as("La méthode " + nomMethode + " n'est pas publique").isTrue();

    }

    public static void checkConstructor(String classe, String... parameters) throws Exception{
        //noinspection deprecation
        checkConstructor(getClasse(classe), Stream.of(parameters).map(t -> {
            try {
                return getClasse(t);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }return null;
        }).collect(Collectors.toList()).toArray(new Class[0]));
    }

    private static void checkConstructor(Class classe, Class... parameters) {
        Constructor constructor = null;
        try {
            constructor = classe.getConstructor(parameters);
        } catch (NoSuchMethodException exception) {
            Assertions.fail("Le constructeur pour la classe " + classe.getName() + " n'existe pas");
        }
        Assertions.assertThat(Modifier.isPublic(constructor.getModifiers())).as("Le constructeur de la classe " + classe.getName() + " n'est pas public").isTrue();

    }

    public static void checkStaticField(String classe, String nomChamp, String type, Object valeur) throws Exception {
        checkStaticFinalField(getClasse(classe), nomChamp, getClasse(type), valeur, false);
    }

    public static void checkStaticFinalField(String classe, String nomChamp, String type, Object valeur) throws Exception {
        checkStaticFinalField(getClasse(classe), nomChamp, getClasse(type), valeur, true);
    }

    private static void checkStaticFinalField(Class classe, String nomChamp, Class type, Object valeur, boolean isFinal) throws IllegalAccessException {
        Field field = null;
        try {
            field = classe.getDeclaredField(nomChamp);
            Assertions.assertThat(field.getType()).isEqualTo(type);
            Assertions.assertThat(field.get(null)).as("Le champ " + nomChamp + " n'a pas la valeur attendue").isEqualTo(valeur);
        } catch (NoSuchFieldException exception) {
            Assertions.fail("Aucun champ nommé " + nomChamp + " n'a été trouvé");
        }
        Assertions.assertThat(Modifier.isFinal(field.getModifiers())).as("Le champ " + nomChamp + " n'est pas une constante alors qu'il devrait l'être (ou l'inverse...)").isEqualTo(isFinal);
        Assertions.assertThat(Modifier.isStatic(field.getModifiers())).as("Le champ " + nomChamp + " n'est pas une constante de classe").isTrue();
    }

    public static Object getStaticFinalField(String classe, String nomChamp) throws Exception {
        //noinspection deprecation
        return getStaticFinalField(getClasse(classe), nomChamp);
    }

    private static Object getStaticFinalField(Class classe, String nomChamp) throws IllegalAccessException {
        try {
            Field field = classe.getField(nomChamp);
            return field.get(null);
        } catch (NoSuchFieldException exception) {
            Assertions.fail("Aucun champ nommé " + nomChamp + " n'a été trouvé");
        }
        return null;
    }

    public static Field checkPrivateField(String classe, String nomChamp, String type) throws Exception {
        //noinspection deprecation
        return checkPrivateField(getClasse(classe), nomChamp, Class.forName(type));
    }

    private static Field checkPrivateField(Class classe, String nomChamp, Class type) throws IllegalAccessException {
        Field field = null;
        try {
            field = classe.getDeclaredField(nomChamp);
            Assertions.assertThat(field.getType()).isEqualTo(type);
        } catch (NoSuchFieldException exception) {
            Assertions.fail("Aucun champ nommé " + nomChamp + " n'a été trouvé");
        }
        Assertions.assertThat(Modifier.isPrivate(field.getModifiers())).as("Le champ " + nomChamp + " n'est pas privé").isTrue();
        Assertions.assertThat(Modifier.isFinal(field.getModifiers())).as("Le champ " + nomChamp + " ne doit pas être final").isFalse();
        return field;
    }

    public static void checkAbstractClass(String classe) throws Exception {
        checkAbstractClass(getClasse(classe));
    }

    private static void checkAbstractClass(Class classe) throws IllegalAccessException {
        Assertions.assertThat(Modifier.isAbstract(classe.getModifiers())).as("La classe " + classe.getName() + " n'est pas abstraite").isTrue();
        Assertions.assertThat(Modifier.isPublic(classe.getModifiers())).as("La classe " + classe.getName() + " n'est pas publique").isTrue();
    }

    public static void checkAnnotation(String classe, Class<? extends Annotation> annotation) throws IllegalAccessException, ClassNotFoundException {
        Class myClass = getClasse(classe);
        Assertions.assertThat(myClass.isAnnotationPresent(annotation)).as("La classe " + myClass.getName() + " n'a pas l'annotation " + annotation.getSimpleName()).isTrue();
    }

    public static void checkNotAbstractClass(String classe) throws Exception{
        //noinspection deprecation
        checkNotAbstractClass(getClasse(classe));
    }

    private static void checkNotAbstractClass(Class classe) throws IllegalAccessException {
        Assertions.assertThat(Modifier.isAbstract(classe.getModifiers())).as("La classe " + classe.getName() + " est abstraite").isFalse();
    }



    public static void invokeSetter(Object obj, String variableName, Object variableValue) throws Exception{
        /* variableValue is Object because value can be an Object, Integer, String, etc... */
        /**
         * Get object of PropertyDescriptor using variable name and class
         * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
         */
        PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
        /* Set field/variable value using getWriteMethod() */
        objPropertyDescriptor.getWriteMethod().invoke(obj, variableValue);
    }

    public static Object invokeGetter(Object obj, String variableName){
        try {
            /**
             * Get object of PropertyDescriptor using variable name and class
             * Note: To use PropertyDescriptor on any field/variable, the field must have both `Setter` and `Getter` method.
             */
            PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(variableName, obj.getClass());
            /**
             * Get field/variable value using getReadMethod()
             * variableValue is Object because value can be an Object, Integer, String, etc...
             */
            Object variableValue = objPropertyDescriptor.getReadMethod().invoke(obj);
            /* Print value of variable */
            return variableValue;
        } catch (Exception e) {
            Assertions.fail("Impossible d'appeler le getter");
        }
        return null;
    }


    public static void checkEnum(String enumName) throws ClassNotFoundException {
        Class<?> c = getClasse(enumName);

        Assertions.assertThat(c.isEnum()).isTrue();
    }

    public static void checkImplementInterface(String classe, String interfaceName) throws ClassNotFoundException {
        Class c = getClasse(classe);
        for (Class i : c.getInterfaces()){
            if(i.getSimpleName().equals(interfaceName)){
                return;
            } else {
                Class i2 = getClasse(i.getName());
                for(Class j : i2.getInterfaces()) {
                    if(j.getSimpleName().equals(interfaceName)){
                        return;
                    } else {
                        Class i3 = getClasse(j.getName());
                        for(Class k : i3.getInterfaces()) {
                            if(k.getSimpleName().equals(interfaceName)){
                                return;
                            }
                        }
                    }
                }
            }
        }
        Assertions.fail("La classe " + classe + " n'implémente pas l'interface " + interfaceName);
    }
}