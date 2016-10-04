package ru.sbt;
/*
 Реализовать интерфейс Terminal, c помощью которого можно:
         1) Проверить состояние счета
         2) Снять/ положить деньги

         Доступ к терминалу должен предоставлять только после корректного ввода пина.
         При попытке вызова любого метода без ввода пина, должен кидаться ексепшен.
         При вводе 3 неправильных пинов, аккаунт лочится на 5сек( при попытке обраться
         к нему вылетает AccountIsLockedException c информацией о времени разлочения).
         Класть и снимать деньги можно только, если сумма кратна 100.

         Т.к банкоматы, которые стоят на улице ничего сами не делают с вашим счетом,
         они всего лишь делают проверку введенных данных и отправляют запросы на
         удаленный сервер(TerminalServer). TerminalServer может кидать исключения связанные
         с проблемами сети; когда недостаточно денег чтобы их снять; что-нибудь еще на ваш вкус)

         класс терминал может содержать следующие поля:
class TerminalImpl {
    private final TerminalServer server;
    private final PinValidator pinValidator;
   ...
}
    Часть команд терминал делегирует этим классам.

        Интерфейс терминала и список исключений остается на ваш дизайн.
        В каждом ексепшене должно быть описание, что нужно сделать, чтобы избежать его в дальнейшем.
        TerminalServer и PinValidator могут кидать свои собственные исключения.
        Конечный пользователь не должен видеть эти исключения, ему должны показываться нормальные
        сообщения об ошибках

*/
public interface Terminal {

    public User withdraw(User user, int amount) throws NoPermissionException, TooMuchTryException, InterruptedException, UnreachableException, NoMoneyException;
    public User deposit(User user, int amount) throws NoPermissionException, TooMuchTryException, InterruptedException, UnreachableException;
    public void checkAmount(User user) throws NoPermissionException, TooMuchTryException, InterruptedException, UnreachableException;
}
