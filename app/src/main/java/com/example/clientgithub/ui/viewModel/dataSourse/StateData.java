package com.example.clientgithub.ui.viewModel.dataSourse;

import com.example.clientgithub.dataSource.authenticationSource.Code;
import com.example.clientgithub.dataSource.authenticationSource.Token;
import com.example.clientgithub.dataSource.dataRepositorySource.Repository;
import com.example.clientgithub.dataSource.dataUserSource.User;

import java.util.List;

public interface StateData {
    class Loading implements StateData {
        private final String loadingMess;
        public Loading(String loadingMess) {
            this.loadingMess = loadingMess;
        }

        public String getLoadingMess() {
            return loadingMess;
        }
    }

    class LoadingUser implements StateData {
        private final String loadingMess;
        public LoadingUser(String loadingMess) {
            this.loadingMess = loadingMess;
        }

        public String getLoadingMess() {
            return loadingMess;
        }
    }
    class SuccessRepository implements StateData {
        private final List<Repository> data;
        public SuccessRepository(List<Repository> data) {
            this.data = data;
        }
        public List<Repository> getData() {
            return data;
        }
    }

    class SuccessUser implements StateData {
        private final User data;
        public SuccessUser(User data) {
            this.data = data;
        }
        public User getData() {
            return data;
        }
    }

    class SuccessCode implements StateData {
        private final Code data;
        public SuccessCode(Code data) {
            this.data = data;
        }
        public Code getData() {
            return data;
        }
    }

    class SuccessToken implements StateData {
        private final Token data;
        public SuccessToken(Token data) {
            this.data = data;
        }
        public Token getData() {
            return data;
        }
    }

    class Error implements StateData {
        private final String error;
        public Error(String errorMess) {
            this.error = errorMess;
        }
        public String getErrorMess() {
            return error;
        }
    }
}